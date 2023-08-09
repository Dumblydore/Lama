package me.mauricee.lama.login

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.core.base.util.AppDispatchers
import me.mauricee.lama.zen.auth.AuthState
import me.mauricee.lama.zen.auth.AuthStore
import me.mauricee.lama.zen.login.LoginApi
import me.mauricee.lama.zen.login.LoginResponse
import me.mauricee.lama.zen.login.LoginResponseStatus
import me.tatarka.inject.annotations.Inject
import kotlin.time.Duration.Companion.hours

@ApplicationScope
@Inject
class LoginRepository(
    private val loginApi: LoginApi,
    private val authStore: AuthStore,
    private val dispatchers: AppDispatchers
) {
    private val _state = MutableStateFlow(ZenAuthState.LoggedOut)
    val state: StateFlow<ZenAuthState> get() = _state.asStateFlow()

    private var lastAuthState: AuthState? = null
    private var lastAuthStateExpiry: Instant = Instant.DISTANT_PAST

    init {
        // Read the auth state from the AuthStore
        GlobalScope.launch(dispatchers.main) {
            val state = getAuthState() ?: AuthState.Empty
            updateAuthState(authState = state, skipPersist = true)
        }
    }
    suspend fun login(email: String, password: String): LoginResult {
        val result = runCatching { loginApi.login(email, password) }
        return if (result.isSuccess) {
            val response = result.getOrThrow()
            when (response.status) {
                LoginResponseStatus.SUCCESS -> {
                    authStore.save(response.toAuthState())
                    LoginResult.Success(
                        token = response.token!!,
                        refreshToken = response.refreshToken!!,
                        organizationId = response.organizationUsers!!.first().organization.id,
                        userId = response.organizationUsers!!.first().user.id
                    )
                }

                LoginResponseStatus.INCORRECT_EMAIL,
                LoginResponseStatus.INCORRECT_PASSWORD -> LoginResult.IncorrectCredentials

                LoginResponseStatus.INVALID,
                LoginResponseStatus.UNKNOWN_ERROR -> LoginResult.Error(Exception("Unknown Error"))
            }
        } else {
            LoginResult.Error(result.exceptionOrNull() ?: Exception("Unknown Error"))
        }
    }

    suspend fun getAuthState(): AuthState? {
        val state = lastAuthState
        if (state != null && lastAuthStateExpiry >= Clock.System.now()) {
//            logger.d { "[TraktAuthRepository] getAuthState. Using cached tokens: $state" }
            return state
        }

//        logger.d { "[TraktAuthRepository] getAuthState. Retrieving tokens from AuthStore" }
        return withContext(dispatchers.io) { authStore.get() }
            ?.also { cacheAuthState(it) }
    }


    private fun cacheAuthState(authState: AuthState) {
        if (authState.isAuthorized) {
            lastAuthState = authState
            lastAuthStateExpiry = Clock.System.now() + 1.hours
        } else {
            lastAuthState = null
            lastAuthStateExpiry = Instant.DISTANT_PAST
        }
    }

    private suspend fun updateAuthState(authState: AuthState, skipPersist: Boolean = false) {
//        logger.d { "[TraktAuthRepository] updateAuthState: $authState. Persist: ${!skipPersist}" }
        _state.value = when {
            authState.isAuthorized -> ZenAuthState.LoggedIn
            else -> ZenAuthState.LoggedOut
        }
        cacheAuthState(authState)
//        logger.d { "[TraktAuthRepository] Updated AuthState: ${_state.value}" }

        if (!skipPersist) {
            // Persist auth state
            withContext(dispatchers.io) {
                if (authState.isAuthorized) {
//                    logger.d { "[TraktAuthRepository] Saving state to AuthStore: $authState" }
                    authStore.save(authState)
                } else {
//                    logger.d { "[TraktAuthRepository] Clearing AuthStore" }
                    authStore.clear()
                }
            }
        }
    }

}

private fun LoginResponse.toAuthState() = AuthState(
    accessToken = token!!,
    refreshToken = refreshToken!!,
    accounts = organizationUsers?.map { it.user.id }.orEmpty()
)

sealed class LoginResult {
    data class Success(
        val token: String,
        val refreshToken: String,
        val organizationId: String,
        val userId: String
    ) : LoginResult()

    object IncorrectCredentials : LoginResult()
    data class Error(val error: Throwable) : LoginResult()
}


enum class ZenAuthState {
    LoggedIn,
    LoggedOut
}
