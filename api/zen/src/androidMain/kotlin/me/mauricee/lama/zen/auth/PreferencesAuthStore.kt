package me.mauricee.lama.zen.auth

import android.content.SharedPreferences
import androidx.core.content.edit
import me.tatarka.inject.annotations.Inject

typealias AuthSharedPreferences = SharedPreferences

@Inject
class PreferencesAuthStore(
    private val authPrefs: Lazy<AuthSharedPreferences>,
) : AuthStore {
    override suspend fun get(): AuthState? {
        val preference = authPrefs.value.getString(PreferenceAuthKey, null)
        val authState = preference?.let(AuthState::decode)
        return authState
    }

    override suspend fun save(state: AuthState) {
        authPrefs.value.edit(commit = true) {
            putString(PreferenceAuthKey, state.serializeToJson())
        }
    }

    override suspend fun clear() {
        authPrefs.value.edit(commit = true) {
            remove(PreferenceAuthKey)
        }
    }

    private companion object {
        private const val PreferenceAuthKey = "stateJson"
    }
}
