package me.mauricee.lama.zen.login

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.withContext
import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.core.base.util.AppDispatchers
import me.tatarka.inject.annotations.Inject

@Inject
@ApplicationScope
class LoginApi(
    private val client: HttpClient,
    private val appDispatchers: AppDispatchers
) {

    suspend fun login(
        email: String,
        password: String
    ): LoginResponse = withContext(appDispatchers.io) {
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
}
