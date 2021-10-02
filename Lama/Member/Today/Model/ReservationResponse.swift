// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let reservationResponse = try? newJSONDecoder().decode(ReservationResponse.self, from: jsonData)

import Foundation

// MARK: - ReservationResponse
struct ReservationResponse: Codable {
    let payloadArray: [ReservationPayloadArray]
    let error: JSONNull?
}

// MARK: - ReservationPayloadArray
struct ReservationPayloadArray: Codable {
    let item: ReservationItem
    let schedule: ReservationSchedule
    let primaryInstructor: ReservationPrimaryInstructor
    let location: ReservationLocation
    let availability: ReservationAvailability
    let capacity: ReservationCapacity
    let reservation: Reservation
    let membership: ReservationMembership
//    let attendees: [JSONAny]
    let note: JSONNull?
}

// MARK: - ReservationAvailability
struct ReservationAvailability: Codable {
    let dropInPrice: JSONNull?
    let isReservable, isCanceled: Bool
}

// MARK: - ReservationCapacity
struct ReservationCapacity: Codable {
    let totalSpots: Int
    let remainingSpots: JSONNull?
    let rsvpCount: Int
    let waitlistTotalSpots, waitlistRemainingSpots, enrollmentMax, attendanceCount: JSONNull?
    let isRemainingSpotsDisplayed, isFull: JSONNull?
}

// MARK: - ReservationItem
struct ReservationItem: Codable {
    let itemID, parentItemID: String
    let type: JSONNull?
    let name: String
    let displayName: JSONNull?
    let itemDescription: String
    let isRanked: JSONNull?
    let programID, programName: String

    enum CodingKeys: String, CodingKey {
        case itemID
        case parentItemID
        case type, name, displayName
        case itemDescription
        case isRanked
        case programID
        case programName
    }
}

// MARK: - ReservationLocation
struct ReservationLocation: Codable {
    let locationID, locationName: String
    let locationPhoneNumber, locationSort: JSONNull?
    let locationAddress: ReservationLocationAddress
    let virtualID, virtualURL, virtualPassword: JSONNull?
    let isLocationDisplayed: Bool

    enum CodingKeys: String, CodingKey {
        case locationID
        case locationName, locationPhoneNumber, locationSort, locationAddress
        case virtualID
        case virtualURL
        case virtualPassword, isLocationDisplayed
    }
}

// MARK: - ReservationLocationAddress
struct ReservationLocationAddress: Codable {
    let line1, line2, city, stateOrProvince: JSONNull?
    let postalCode, latitude, longitude: JSONNull?
}

// MARK: - ReservationMembership
struct ReservationMembership: Codable {
    let membershipID, membershipUsageCount, attendanceMaximum, attendanceMaximumDuration: JSONNull?
    let attendanceMaximumDurationUnit: String
    let onHold, isMembershipExceeded: JSONNull?

    enum CodingKeys: String, CodingKey {
        case membershipID
        case membershipUsageCount, attendanceMaximum, attendanceMaximumDuration, attendanceMaximumDurationUnit, onHold, isMembershipExceeded
    }
}

// MARK: - ReservationPrimaryInstructor
struct ReservationPrimaryInstructor: Codable {
    let primaryInstructorID, primaryInstructorDisplayName, firstName, lastName: String
    let primaryInstructorPhoto: JSONNull?
    let isPrimaryInstructorDisplayed: Bool
    let isPrimaryInstructorBiographyWritten: JSONNull?

    enum CodingKeys: String, CodingKey {
        case primaryInstructorID
        case primaryInstructorDisplayName, firstName, lastName, primaryInstructorPhoto, isPrimaryInstructorDisplayed, isPrimaryInstructorBiographyWritten
    }
}

// MARK: - Reservation
struct Reservation: Codable {
    let attendanceID: String
    let waitlistID: JSONNull?
    let isCheckedIn: Bool
    let confirmBefore, upgradeDateTime, upgradedWaitlistID, cancelationType: JSONNull?
    let isReserved, isWaitlisted: Bool
    let waitlistPosition, initialWaitlistPosition: Int
    let useMemberWaitlistConfirmation: Bool
    let memberCommunicationMedium, replyTimeDuration, replyTimeUnit: JSONNull?
    let reservationLimitCount: Int
    let confirmBeforeTime, waitlistUpgradeDate: JSONNull?

    enum CodingKeys: String, CodingKey {
        case attendanceID
        case waitlistID
        case isCheckedIn, confirmBefore, upgradeDateTime
        case upgradedWaitlistID
        case cancelationType, isReserved, isWaitlisted, waitlistPosition, initialWaitlistPosition, useMemberWaitlistConfirmation, memberCommunicationMedium, replyTimeDuration, replyTimeUnit, reservationLimitCount, confirmBeforeTime, waitlistUpgradeDate
    }
}

// MARK: - ReservationSchedule
struct ReservationSchedule: Codable {
    let start, end: String
    let duration: Int
    let reserveAfterAmount, reserveAfterUnit, reserveAfter, reserveBeforeAmount: JSONNull?
    let reserveBeforeUnit: JSONNull?
    let reserveBefore: String
    let cancelBeforeAmount, cancelBeforeUnit: JSONNull?
    let cancelBefore: String
    let lateCancelEnabled, lateCancelDockAttendance, lateCancelAddBill, lateCancelBillAmount: JSONNull?
    let checkinAfterAmount, checkinAfterUnit: JSONNull?
    let checkinAfter: String
}

// MARK: - Encode/decode helpers

class JSONNull: Codable, Hashable {

    public static func == (lhs: JSONNull, rhs: JSONNull) -> Bool {
        return true
    }

    public var hashValue: Int {
        return 0
    }

    public init() {}

    public required init(from decoder: Decoder) throws {
        let container = try decoder.singleValueContainer()
        if !container.decodeNil() {
            throw DecodingError.typeMismatch(JSONNull.self, DecodingError.Context(codingPath: decoder.codingPath, debugDescription: "Wrong type for JSONNull"))
        }
    }

    public func encode(to encoder: Encoder) throws {
        var container = encoder.singleValueContainer()
        try container.encodeNil()
    }
}

class JSONCodingKey: CodingKey {
    let key: String

    required init?(intValue: Int) {
        return nil
    }

    required init?(stringValue: String) {
        key = stringValue
    }

    var intValue: Int? {
        return nil
    }

    var stringValue: String {
        return key
    }
}

