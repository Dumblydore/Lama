// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let classResponse = try? newJSONDecoder().decode(ClassResponse.self, from: jsonData)

import Foundation

// MARK: - ClassResponse
struct ClassResponse: Codable {
    let classes: [ClassItem]
    let reservationCount: Int
}

// MARK: - Class
struct ClassItem: Codable {
    let item: ClassItemMetaData
    let schedule: ClassSchedule
    let primaryInstructor: ClassPrimaryInstructor
    let location: ClassLocation
    let availability: ClassAvailability
    let capacity: ClassCapacity
    let reservation: ClassReservation
    let membership: ClassMembership
    let attendees: [JSONAny]
    let note: JSONNull?
}

// MARK: - ClassAvailability
struct ClassAvailability: Codable {
    let dropInPrice: Int
    let isReservable, isCanceled: Bool
}

// MARK: - ClassCapacity
struct ClassCapacity: Codable {
    let totalSpots, remainingSpots: Int
    let rsvpCount: JSONNull?
    let waitlistTotalSpots, waitlistRemainingSpots, enrollmentMax: Int
    let attendanceCount: JSONNull?
    let isRemainingSpotsDisplayed, isFull: Bool
}

// MARK: - ClassItem
struct ClassItemMetaData: Codable {
    let itemID: String
    let parentItemID: JSONNull?
    let type, name: String
    let displayName: JSONNull?
    let itemDescription: String
    let isRanked, programID, programName: JSONNull?

    enum CodingKeys: String, CodingKey {
        case itemID = "itemId"
        case parentItemID = "parentItemId"
        case type, name, displayName
        case itemDescription = "description"
        case isRanked
        case programID = "programId"
        case programName
    }
}

// MARK: - ClassLocation
struct ClassLocation: Codable {
    let locationID, locationName, locationPhoneNumber: String
    let locationSort: JSONNull?
    let locationAddress: ClassLocationAddress
    let virtualID, virtualURL, virtualPassword: JSONNull?
    let isLocationDisplayed: Bool

    enum CodingKeys: String, CodingKey {
        case locationID = "locationId"
        case locationName, locationPhoneNumber, locationSort, locationAddress
        case virtualID = "virtualId"
        case virtualURL = "virtualUrl"
        case virtualPassword, isLocationDisplayed
    }
}

// MARK: - ClassLocationAddress
struct ClassLocationAddress: Codable {
    let line1, line2, city, stateOrProvince: JSONNull?
    let postalCode, latitude, longitude: JSONNull?
}

// MARK: - ClassMembership
struct ClassMembership: Codable {
    let membershipID: String
    let membershipUsageCount, attendanceMaximum: Int
    let attendanceMaximumDuration, attendanceMaximumDurationUnit: String
    let onHold, isMembershipExceeded: Bool

    enum CodingKeys: String, CodingKey {
        case membershipID = "membershipId"
        case membershipUsageCount, attendanceMaximum, attendanceMaximumDuration, attendanceMaximumDurationUnit, onHold, isMembershipExceeded
    }
}

// MARK: - ClassPrimaryInstructor
struct ClassPrimaryInstructor: Codable {
    let primaryInstructorID, primaryInstructorDisplayName: String
    let firstName, lastName, primaryInstructorPhoto: JSONNull?
    let isPrimaryInstructorDisplayed, isPrimaryInstructorBiographyWritten: Bool

    enum CodingKeys: String, CodingKey {
        case primaryInstructorID = "primaryInstructorId"
        case primaryInstructorDisplayName, firstName, lastName, primaryInstructorPhoto, isPrimaryInstructorDisplayed, isPrimaryInstructorBiographyWritten
    }
}

// MARK: - ClassReservation
struct ClassReservation: Codable {
    let attendanceID, waitlistID: JSONNull?
    let isCheckedIn: Bool
    let confirmBefore, upgradeDateTime, upgradedWaitlistID, cancelationType: JSONNull?
    let isReserved, isWaitlisted: Bool
    let waitlistPosition, initialWaitlistPosition: JSONNull?
    let useMemberWaitlistConfirmation: Bool
    let memberCommunicationMedium, replyTimeDuration, replyTimeUnit: JSONNull?
    let reservationLimitCount: Int
    let confirmBeforeTime, waitlistUpgradeDate: JSONNull?

    enum CodingKeys: String, CodingKey {
        case attendanceID = "attendanceId"
        case waitlistID = "waitlistId"
        case isCheckedIn, confirmBefore, upgradeDateTime
        case upgradedWaitlistID = "upgradedWaitlistId"
        case cancelationType, isReserved, isWaitlisted, waitlistPosition, initialWaitlistPosition, useMemberWaitlistConfirmation, memberCommunicationMedium, replyTimeDuration, replyTimeUnit, reservationLimitCount, confirmBeforeTime, waitlistUpgradeDate
    }
}

// MARK: - ClassSchedule
struct ClassSchedule: Codable {
    let start, end: String
    let duration: Int
    let reserveAfterAmount, reserveAfterUnit, reserveAfter: JSONNull?
    let reserveBeforeAmount: Int
    let reserveBeforeUnit, reserveBefore: String
    let cancelBeforeAmount, cancelBeforeUnit, cancelBefore: JSONNull?
    let lateCancelEnabled: Bool
    let lateCancelDockAttendance, lateCancelAddBill, lateCancelBillAmount, checkinAfterAmount: JSONNull?
    let checkinAfterUnit, checkinAfter: JSONNull?
}
