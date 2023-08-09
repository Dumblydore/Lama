package me.mauricee.lama.zen.user


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("isLast") val isLast: Boolean,
    @SerialName("last") val last: Boolean,
    @SerialName("payload") val payload: Payload,
    @SerialName("totalElements") val totalElements: Int,
    @SerialName("totalPages") val totalPages: Int
) {
    @Serializable
    data class Payload(
        @SerialName("partition") val partition: Partition,
        @SerialName("person") val person: Person,
        @SerialName("userAccount") val userAccount: UserAccount
    ) {
        @Serializable
        data class Partition(
            @SerialName("address1") val address1: String,
            @SerialName("apiKey") val apiKey: String,
            @SerialName("brandColor") val brandColor: String,
            @SerialName("businessPhone") val businessPhone: String,
            @SerialName("ccComponent") val ccComponent: String,
            @SerialName("ccConfiguration") val ccConfiguration: String,
            @SerialName("city") val city: String,
            @SerialName("country") val country: String,
            @SerialName("currency") val currency: String,
            @SerialName("eftConfiguration") val eftConfiguration: String,
            @SerialName("isAvsSupported") val isAvsSupported: Boolean,
            @SerialName("isElementsSupported") val isElementsSupported: Boolean,
            @SerialName("locale") val locale: String,
            @SerialName("memberCurriculumEnabled") val memberCurriculumEnabled: Boolean,
            @SerialName("merchantId") val merchantId: String,
            @SerialName("partitionId") val partitionId: String,
            @SerialName("portalCreditCard") val portalCreditCard: Boolean,
            @SerialName("portalFinancial") val portalFinancial: Boolean,
            @SerialName("portalPaymentTypes") val portalPaymentTypes: String,
            @SerialName("portalPayments") val portalPayments: Boolean,
            @SerialName("primaryEmail") val primaryEmail: String,
            @SerialName("schoolName") val schoolName: String,
            @SerialName("skillLabel") val skillLabel: String,
            @SerialName("socialEnabled") val socialEnabled: Boolean,
            @SerialName("state") val state: String,
            @SerialName("status") val status: String,
            @SerialName("sugarWODEnabled") val sugarWODEnabled: Boolean,
            @SerialName("template") val template: String,
            @SerialName("timezone") val timezone: String,
            @SerialName("workoutTracking") val workoutTracking: Boolean,
            @SerialName("zipCode") val zipCode: String
        )

        @Serializable
        data class Person(
            @SerialName("familyId") val familyId: String,
            @SerialName("firstName") val firstName: String,
            @SerialName("lastName") val lastName: String,
            @SerialName("personId") val personId: String,
            @SerialName("personUniversalProfile") val personUniversalProfile: PersonUniversalProfile,
            @SerialName("primaryEmailInfo") val primaryEmailInfo: PrimaryEmailInfo
        ) {
            @Serializable
            data class PersonUniversalProfile(
                @SerialName("description") val description: String,
                @SerialName("displayName") val displayName: String,
                @SerialName("isPhotographPublic") val isPhotographPublic: Boolean,
                @SerialName("isProfilePublic") val isProfilePublic: Boolean,
                @SerialName("locale") val locale: String,
                @SerialName("personUniversalProfileId") val personUniversalProfileId: String,
                @SerialName("photoUrl") val photoUrl: String,
                @SerialName("sort") val sort: Int,
                @SerialName("title") val title: String,
                @SerialName("universalProfileId") val universalProfileId: String
            )

            @Serializable
            data class PrimaryEmailInfo(
                @SerialName("contactInformation") val contactInformation: String,
                @SerialName("contactInformationId") val contactInformationId: String,
                @SerialName("id") val id: String,
                @SerialName("subType") val subType: String,
                @SerialName("type") val type: String
            )
        }

        @Serializable
        data class UserAccount(
            @SerialName("accessType") val accessType: String,
            @SerialName("isLocked") val isLocked: Boolean,
            @SerialName("isStaff") val isStaff: Boolean,
            @SerialName("personId") val personId: String,
            @SerialName("userAccountId") val userAccountId: String,
            @SerialName("username") val username: String
        )
    }
}