package me.mauricee.lama.zen.auth

import me.tatarka.inject.annotations.Inject

@Inject
class JvmAuthStore() : AuthStore {
    override suspend fun get(): AuthState? {
        TODO("Not yet implemented")
    }

    override suspend fun save(state: AuthState) {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}

private const val KEY_ACCESS_TOKEN = "access_token"
private const val KEY_REFRESH_TOKEN = "refresh_token"