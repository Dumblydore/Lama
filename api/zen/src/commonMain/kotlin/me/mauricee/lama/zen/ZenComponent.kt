package me.mauricee.lama.zen

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.zen.auth.AuthStore
import me.tatarka.inject.annotations.Provides

expect interface ZenPlatformComponent
interface ZenComponent : ZenCommonComponent, ZenPlatformComponent

interface ZenCommonComponent {
    @Provides
    @ApplicationScope
    fun provideHttpClient(authStore: AuthStore): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }

        install(DefaultRequest) {
            header("Content-Type", "application/json")
            header("Accept", "application/json")
            if (headers.contains(RequiresAuthKey)) {
                header("Authorization", "Bearer ${runBlocking { authStore.get()?.accessToken }}")
            }
            if (headers.contains(RequiresRefreshKey)) {
                header("X-Auth-Refresh", runBlocking { authStore.get()?.refreshToken })
            }
        }

        install(ResponseObserver) {
            onResponse { response ->
                runBlocking { authStore.get() }?.let { oldAuthState ->
                    val newAccessToken = response.call.response.headers["Authorization"]
                        ?.split("Bearer ")?.firstOrNull().orEmpty()
                    if (newAccessToken.isNotEmpty() && oldAuthState.accessToken != newAccessToken) {
                        runBlocking { authStore.save(oldAuthState.copy(accessToken = newAccessToken)) }
                    }
                }
            }
        }
    }
}