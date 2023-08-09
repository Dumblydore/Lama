package me.mauricee.lama.zen.auth

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.set
import kotlinx.coroutines.withContext
import me.mauricee.lama.core.base.app.ApplicationInfo
import me.mauricee.lama.core.base.util.AppDispatchers
import me.tatarka.inject.annotations.Inject

@OptIn(ExperimentalSettingsImplementation::class)
@Inject
class IosZenAuthStore(
    private val applicationInfo: ApplicationInfo,
    private val dispatchers: AppDispatchers,
) : AuthStore {
    private val keyChainSettings by lazy { KeychainSettings(applicationInfo.packageName) }

    override suspend fun get(): AuthState? = withContext(dispatchers.io) {
        val accessToken = keyChainSettings.getStringOrNull(KEY_ACCESS_TOKEN)
        val refreshToken = keyChainSettings.getStringOrNull(KEY_REFRESH_TOKEN)
        if (accessToken != null && refreshToken != null) {
            SimpleAuthState(accessToken, refreshToken)
        } else {
            null
        }
    }

    override suspend fun save(state: AuthState) = withContext(dispatchers.io) {
        keyChainSettings[KEY_ACCESS_TOKEN] = state.accessToken
        keyChainSettings[KEY_REFRESH_TOKEN] = state.refreshToken
    }

    override suspend fun clear() = withContext(dispatchers.io) {
        keyChainSettings.remove(KEY_ACCESS_TOKEN)
        keyChainSettings.remove(KEY_REFRESH_TOKEN)
    }
}

private const val KEY_ACCESS_TOKEN = "access_token"
private const val KEY_REFRESH_TOKEN = "refresh_token"