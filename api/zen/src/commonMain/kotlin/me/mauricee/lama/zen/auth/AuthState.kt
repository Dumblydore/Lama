package me.mauricee.lama.zen.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class AuthState(
    @SerialName("accessToken") val accessToken: String,
    @SerialName("refreshToken") val refreshToken: String,
    @SerialName("accounts") val accounts: List<String>,
) {
    val isAuthorized: Boolean get() = accessToken.isNotEmpty() && refreshToken.isNotEmpty()
    fun serializeToJson(): String = Json.encodeToString(this)

    companion object {
        val Empty: AuthState = AuthState("", "", emptyList())
        fun decode(json: String): AuthState = Json.decodeFromString(json)
    }
}