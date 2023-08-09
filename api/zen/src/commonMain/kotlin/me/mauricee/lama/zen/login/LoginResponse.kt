package me.mauricee.lama.zen.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("organizationUsers") val organizationUsers: List<OrganizationUser>?,
    @SerialName("refreshToken") val refreshToken: String?,
    @SerialName("status") val status: LoginResponseStatus,
    @SerialName("token") val token: String?
) {
    @Serializable
    data class OrganizationUser(
        @SerialName("organization") val organization: Organization,
        @SerialName("roles") val roles: List<String>,
        @SerialName("user") val user: User
    ) {
        @Serializable
        data class Organization(
            @SerialName("id") val id: String,
            @SerialName("name") val name: String,
            @SerialName("region") val region: Region
        ) {
            @Serializable
            data class Region(
                @SerialName("locale") val locale: String,
                @SerialName("timezone") val timezone: String
            )
        }

        @Serializable
        data class User(
            @SerialName("firstName") val firstName: String,
            @SerialName("id") val id: String,
            @SerialName("lastName") val lastName: String,
            @SerialName("username") val username: String
        )
    }
}

enum class LoginResponseStatus {
    @SerialName("SUCCESS")
    SUCCESS,
    @SerialName("EMAIL_INCORRECT")
    INCORRECT_EMAIL,
    @SerialName("PASSWORD_INCORRECT")
    INCORRECT_PASSWORD,
    @SerialName("INVALID")
    INVALID,
    UNKNOWN_ERROR
}