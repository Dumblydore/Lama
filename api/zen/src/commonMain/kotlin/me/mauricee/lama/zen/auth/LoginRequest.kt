package me.mauricee.lama.zen.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
//    @SerialName("authCheckOnly") val authCheckOnly: Boolean,
//    @SerialName("orgId") val orgId: Any?,
    @SerialName("password") val password: String,
//    @SerialName("requiredRoles") val requiredRoles: List<String>,
//    @SerialName("temporaryAuth") val temporaryAuth: Boolean,
//    @SerialName("userId") val userId: Any?,
    @SerialName("username") val username: String
)