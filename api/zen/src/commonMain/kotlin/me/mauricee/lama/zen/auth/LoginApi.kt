package me.mauricee.lama.zen.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.host
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class LoginApi {
    private val client by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
        }
    }

    suspend fun login(
        email: String = "medwar40@gmail.com",
        password: String = "SgsH5?ooTWUwruOt"
    ): LoginResult = withContext(Dispatchers.IO) {
        val result = runCatching {
            client.post("https://memberappv220.zenplanner.com/auth/v1/login") {
                headers {
                    append("Content-Type", "application/json")
                    append("Accept", "application/json")
                }
                setBody(
                    LoginRequest(
                        username = email,
                        password = password
                    )
                )
            }.body<LoginResponse>()
        }

        if (result.isSuccess) {
            val response = result.getOrThrow()
            when (response.status) {
                LoginResponseStatus.SUCCESS -> {
                    LoginResult.Success(
                        token = response.token!!,
                        refreshToken = response.refreshToken!!,
                        organizationId = response.organizationUsers!!.first().organization.id,
                        userId = response.organizationUsers.first().user.id
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
}


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