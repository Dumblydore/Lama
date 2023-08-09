package me.mauricee.lama.zen.auth

interface AuthStore {
    suspend fun get(): AuthState?
    suspend fun save(state: AuthState)

    suspend fun clear()
    suspend fun isAvailable(): Boolean = true
}
