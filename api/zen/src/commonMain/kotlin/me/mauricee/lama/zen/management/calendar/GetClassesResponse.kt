package me.mauricee.lama.zen.management.calendar


import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetClassesResponse(
    @SerialName("classes") val classes: List<ScheduledClass>,
    @SerialName("reservationCount") val reservationCount: Int
) {
    @Serializable
    data class ScheduledClass(
//        @SerialName("attendees") val attendees: List<Any>,
        @SerialName("availability") val availability: Availability,
        @SerialName("capacity") val capacity: Capacity,
        @SerialName("item") val item: Item,
        @SerialName("location") val location: Location,
        @SerialName("membership") val membership: Membership,
        @SerialName("note") val note: String?,
        @SerialName("primaryInstructor") val primaryInstructor: PrimaryInstructor,
        @SerialName("reservation") val reservation: Reservation,
        @SerialName("schedule") val schedule: Schedule
    ) {
        @Serializable
        data class Availability(
            @SerialName("dropInPrice") val dropInPrice: Int,
            @SerialName("isCanceled") val isCanceled: Boolean,
            @SerialName("isReservable") val isReservable: Boolean
        )

        @Serializable
        data class Capacity(
            @SerialName("attendanceCount") val attendanceCount: Int?,
            @SerialName("enrollmentMax") val enrollmentMax: Int,
            @SerialName("isFull") val isFull: Boolean,
            @SerialName("isRemainingSpotsDisplayed") val isRemainingSpotsDisplayed: Boolean,
            @SerialName("remainingSpots") val remainingSpots: Int,
            @SerialName("rsvpCount") val rsvpCount: Int?,
            @SerialName("totalSpots") val totalSpots: Int,
            @SerialName("waitlistRemainingSpots") val waitlistRemainingSpots: Int,
            @SerialName("waitlistTotalSpots") val waitlistTotalSpots: Int
        )

        @Serializable
        data class Item(
            @SerialName("cancelDate") val cancelDate: Instant?,
            @SerialName("description") val description: String?,
            @SerialName("displayName") val displayName: String?,
            @SerialName("isRanked") val isRanked: Boolean?,
            @SerialName("itemId") val itemId: String,
            @SerialName("name") val name: String,
            @SerialName("parentItemId") val parentItemId: Int?,
            @SerialName("programId") val programId: Int?,
            @SerialName("programName") val programName: String?,
            @SerialName("stylesheet") val stylesheet: String,
            @SerialName("type") val type: String
        )

        @Serializable
        data class Location(
            @SerialName("isLocationDisplayed") val isLocationDisplayed: Boolean,
            @SerialName("locationAddress") val locationAddress: LocationAddress,
            @SerialName("locationId") val locationId: String,
            @SerialName("locationName") val locationName: String,
            @SerialName("locationPhoneNumber") val locationPhoneNumber: String,
//            @SerialName("locationSort") val locationSort: Any?,
            @SerialName("virtualId") val virtualId: Int?,
//            @SerialName("virtualPassword") val virtualPassword: Any?,
//            @SerialName("virtualUrl") val virtualUrl: Any?
        ) {
            @Serializable
            data class LocationAddress(
                @SerialName("city") val city: String?,
//                @SerialName("latitude") val latitude: Any?,
//                @SerialName("line1") val line1: Any?,
//                @SerialName("line2") val line2: Any?,
//                @SerialName("longitude") val longitude: Any?,
//                @SerialName("postalCode") val postalCode: Any?,
//                @SerialName("stateOrProvince") val stateOrProvince: Any?
            )
        }

        @Serializable
        data class Membership(
            @SerialName("attendanceMaximum") val attendanceMaximum: Int,
            @SerialName("attendanceMaximumDuration") val attendanceMaximumDuration: String,
            @SerialName("attendanceMaximumDurationUnit") val attendanceMaximumDurationUnit: String,
            @SerialName("isMembershipExceeded") val isMembershipExceeded: Boolean,
            @SerialName("membershipId") val membershipId: String,
            @SerialName("membershipUsageCount") val membershipUsageCount: Int,
            @SerialName("onHold") val onHold: Boolean
        )

        @Serializable
        data class PrimaryInstructor(
            @SerialName("firstName") val firstName: String?,
            @SerialName("isPrimaryInstructorBiographyWritten") val isPrimaryInstructorBiographyWritten: Boolean,
            @SerialName("isPrimaryInstructorDisplayed") val isPrimaryInstructorDisplayed: Boolean,
            @SerialName("lastName") val lastName: String?,
            @SerialName("primaryInstructorDisplayName") val primaryInstructorDisplayName: String,
            @SerialName("primaryInstructorId") val primaryInstructorId: String,
            @SerialName("primaryInstructorPhoto") val primaryInstructorPhoto: String?
        )

        @Serializable
        data class Reservation(
            @SerialName("attendanceId") val attendanceId: String?,
//            @SerialName("cancelationType") val cancelationType: Any?,
//            @SerialName("confirmBefore") val confirmBefore: Any?,
//            @SerialName("confirmBeforeTime") val confirmBeforeTime: Any?,
//            @SerialName("initialWaitlistPosition") val initialWaitlistPosition: Any?,
            @SerialName("isCheckedIn") val isCheckedIn: Boolean,
            @SerialName("isReserved") val isReserved: Boolean,
            @SerialName("isWaitlisted") val isWaitlisted: Boolean,
//            @SerialName("memberCommunicationMedium") val memberCommunicationMedium: Any?,
////            @SerialName("replyTimeDuration") val replyTimeDuration: Any?,
////            @SerialName("replyTimeUnit") val replyTimeUnit: Any?,
            @SerialName("reservationLimitCount") val reservationLimitCount: Int,
////            @SerialName("upgradeDateTime") val upgradeDateTime: Any?,
            @SerialName("upgradedWaitlistId") val upgradedWaitlistId: Int?,
            @SerialName("useMemberWaitlistConfirmation") val useMemberWaitlistConfirmation: Boolean,
            @SerialName("waitlistId") val waitlistId: Int?,
//            @SerialName("waitlistPosition") val waitlistPosition: Any?,
//            @SerialName("waitlistUpgradeDate") val waitlistUpgradeDate: Any?
        )

        @Serializable
        data class Schedule(
//            @SerialName("cancelBefore") val cancelBefore: Any?,
//            @SerialName("cancelBeforeAmount") val cancelBeforeAmount: Any?,
//            @SerialName("cancelBeforeUnit") val cancelBeforeUnit: Any?,
//            @SerialName("checkinAfter") val checkinAfter: Any?,
//            @SerialName("checkinAfterAmount") val checkinAfterAmount: Any?,
//            @SerialName("checkinAfterUnit") val checkinAfterUnit: Any?,
            @SerialName("duration") val duration: Int,
            @SerialName("end") val end: Instant,
//            @SerialName("lateCancelAddBill") val lateCancelAddBill: Any?,
//            @SerialName("lateCancelBillAmount") val lateCancelBillAmount: Any?,
//            @SerialName("lateCancelDockAttendance") val lateCancelDockAttendance: Any?,
            @SerialName("lateCancelEnabled") val lateCancelEnabled: Boolean,
//            @SerialName("reserveAfter") val reserveAfter: Any?,
//            @SerialName("reserveAfterAmount") val reserveAfterAmount: Any?,
//            @SerialName("reserveAfterUnit") val reserveAfterUnit: Any?,
            @SerialName("reserveBefore") val reserveBefore: String?,
            @SerialName("reserveBeforeAmount") val reserveBeforeAmount: Int?,
            @SerialName("reserveBeforeUnit") val reserveBeforeUnit: String?,
            @SerialName("start") val start: Instant
        )
    }
}