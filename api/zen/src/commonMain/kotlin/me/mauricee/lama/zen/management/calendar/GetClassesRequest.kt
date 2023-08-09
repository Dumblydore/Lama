package me.mauricee.lama.zen.management.calendar
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetClassesRequest(
    @SerialName("beginDate") val beginDate: Instant,
    @SerialName("endDate") val endDate: Instant,
    @SerialName("personId") val personId: String
)