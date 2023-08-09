package me.mauricee.lama.zen.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.core.base.util.AppDispatchers
import me.mauricee.lama.zen.refresh
import me.tatarka.inject.annotations.Inject

@Inject
@ApplicationScope
class UserApi(
    private val client: HttpClient,
    private val appDispatchers: AppDispatchers
) {

    suspend fun currentUser(): UserResponse = withContext(appDispatchers.io) {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        client.get("https://memberappapiv220.zenplanner.com/elements/api-v2/profiles/current?_=$timestamp") {
            refresh()
        }.body()
    }
}