package me.mauricee.lama.zen.management.calendar


import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetClassResponse(
    @SerialName("class")
    val classDetails: ClassDetails
) {
    @Serializable
    data class ClassDetails(
        @SerialName("attendees")
        val attendees: List<Attendee>,
        @SerialName("availability")
        val availability: Availability,
        @SerialName("capacity")
        val capacity: Capacity,
        @SerialName("item")
        val item: Item,
        @SerialName("location")
        val location: Location,
        @SerialName("membership")
        val membership: Membership,
//        @SerialName("note")
//        val note: String,
        @SerialName("primaryInstructor")
        val primaryInstructor: GetClassesResponse.ScheduledClass.PrimaryInstructor,
        @SerialName("reservation")
        val reservation: GetClassesResponse.ScheduledClass.Reservation,
        @SerialName("schedule")
        val schedule: GetClassesResponse.ScheduledClass.Schedule
    ) {
        @Serializable
        data class Attendee(
            @SerialName("attendeeDisplayName")
            val attendeeDisplayName: String,
            @SerialName("attendeeId")
            val attendeeId: String,
            @SerialName("attendeePhoto")
            val attendeePhoto: String?
        )

        @Serializable
        data class Availability(
            @SerialName("dropInPrice")
            val dropInPrice: Int?,
            @SerialName("isCanceled")
            val isCanceled: Boolean?,
            @SerialName("isReservable")
            val isReservable: Boolean?
        )

        @Serializable
        data class Capacity(
            @SerialName("attendanceCount")
            val attendanceCount: String?,
            @SerialName("enrollmentMax")
            val enrollmentMax: Int?,
            @SerialName("isFull")
            val isFull: Boolean?,
            @SerialName("isRemainingSpotsDisplayed")
            val isRemainingSpotsDisplayed: Boolean?,
            @SerialName("remainingSpots")
            val remainingSpots: Int?,
            @SerialName("rsvpCount")
            val rsvpCount: String?,
            @SerialName("totalSpots")
            val totalSpots: Int?,
            @SerialName("waitlistRemainingSpots")
            val waitlistRemainingSpots: Int?,
            @SerialName("waitlistTotalSpots")
            val waitlistTotalSpots: Int?
        )

        @Serializable
        data class Item(
            @SerialName("cancelDate")
            val cancelDate: String?,
            @SerialName("description")
            val description: String?,
            @SerialName("displayName")
            val displayName: String?,
            @SerialName("isRanked")
            val isRanked: String?,
            @SerialName("itemId")
            val itemId: String?,
            @SerialName("name")
            val name: String?,
            @SerialName("parentItemId")
            val parentItemId: String?,
            @SerialName("programId")
            val programId: String?,
            @SerialName("programName")
            val programName: String?,
            @SerialName("stylesheet")
            val stylesheet: String?,
            @SerialName("type")
            val type: String?
        )

        @Serializable
        data class Location(
            @SerialName("isLocationDisplayed")
            val isLocationDisplayed: Boolean?,
            @SerialName("locationAddress")
            val locationAddress: LocationAddress?,
            @SerialName("locationId")
            val locationId: String?,
            @SerialName("locationName")
            val locationName: String?,
            @SerialName("locationPhoneNumber")
            val locationPhoneNumber: String?,
            @SerialName("locationSort")
            val locationSort: String?,
            @SerialName("virtualId")
            val virtualId: String?,
            @SerialName("virtualPassword")
            val virtualPassword: String?,
            @SerialName("virtualUrl")
            val virtualUrl: String?
        ) {
            @Serializable
            data class LocationAddress(
                @SerialName("city")
                val city: String?,
                @SerialName("latitude")
                val latitude: String?,
                @SerialName("line1")
                val line1: String?,
                @SerialName("line2")
                val line2: String?,
                @SerialName("longitude")
                val longitude: String?,
                @SerialName("postalCode")
                val postalCode: String?,
                @SerialName("stateOrProvince")
                val stateOrProvince: String?
            )
        }

        @Serializable
        data class Membership(
            @SerialName("attendanceMaximum")
            val attendanceMaximum: Int?,
            @SerialName("attendanceMaximumDuration")
            val attendanceMaximumDuration: String?,
            @SerialName("attendanceMaximumDurationUnit")
            val attendanceMaximumDurationUnit: String?,
            @SerialName("isMembershipExceeded")
            val isMembershipExceeded: Boolean?,
            @SerialName("membershipId")
            val membershipId: String?,
            @SerialName("membershipUsageCount")
            val membershipUsageCount: Int?,
            @SerialName("onHold")
            val onHold: Boolean?
        )
    }
}