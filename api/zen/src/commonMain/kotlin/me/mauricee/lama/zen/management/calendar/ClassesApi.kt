package me.mauricee.lama.zen.management.calendar

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.URLBuilder
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.zen.requiresAuth
import me.tatarka.inject.annotations.Inject
import kotlin.time.Duration.Companion.seconds

@Inject
@ApplicationScope
class ClassesApi(private val client: HttpClient) {
    suspend fun getClassesForDate(
        personId: String,
        date: LocalDate
    ): List<GetClassesResponse.ScheduledClass> {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val from = date.atStartOfDayIn(currentTimeZone)
        val to = date.plus(DatePeriod(days = 1)).atStartOfDayIn(currentTimeZone).minus(1.seconds)

        return client.post("https://memberappapiv220.zenplanner.com/elements/api-v2/calendars/classes") {
            requiresAuth()
            setBody(
                GetClassesRequest(
                    from, to, personId
                )
            )
        }.body<GetClassesResponse>().classes
    }

    suspend fun getClass(personId: String, classId: String): GetClassResponse {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        return client.get("$Host/calendars/classes/$personId/$classId?_=$timestamp") {
            requiresAuth()
        }.body()
    }
}

private const val Host = "https://memberappapiv220.zenplanner.com/elements/api-v2"