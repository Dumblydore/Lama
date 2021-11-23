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
    let attendees: [JSONAny]
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
        case itemID = "itemId"
        case parentItemID = "parentItemId"
        case type, name, displayName
        case itemDescription = "description"
        case isRanked
        case programID = "programId"
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
        case locationID = "locationId"
        case locationName, locationPhoneNumber, locationSort, locationAddress
        case virtualID = "virtualId"
        case virtualURL = "virtualUrl"
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
        case membershipID = "membershipId"
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
        case primaryInstructorID = "primaryInstructorId"
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
        case attendanceID = "attendanceId"
        case waitlistID = "waitlistId"
        case isCheckedIn, confirmBefore, upgradeDateTime
        case upgradedWaitlistID = "upgradedWaitlistId"
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

class JSONAny: Codable {

    let value: Any

    static func decodingError(forCodingPath codingPath: [CodingKey]) -> DecodingError {
        let context = DecodingError.Context(codingPath: codingPath, debugDescription: "Cannot decode JSONAny")
        return DecodingError.typeMismatch(JSONAny.self, context)
    }

    static func encodingError(forValue value: Any, codingPath: [CodingKey]) -> EncodingError {
        let context = EncodingError.Context(codingPath: codingPath, debugDescription: "Cannot encode JSONAny")
        return EncodingError.invalidValue(value, context)
    }

    static func decode(from container: SingleValueDecodingContainer) throws -> Any {
        if let value = try? container.decode(Bool.self) {
            return value
        }
        if let value = try? container.decode(Int64.self) {
            return value
        }
        if let value = try? container.decode(Double.self) {
            return value
        }
        if let value = try? container.decode(String.self) {
            return value
        }
        if container.decodeNil() {
            return JSONNull()
        }
        throw decodingError(forCodingPath: container.codingPath)
    }

    static func decode(from container: inout UnkeyedDecodingContainer) throws -> Any {
        if let value = try? container.decode(Bool.self) {
            return value
        }
        if let value = try? container.decode(Int64.self) {
            return value
        }
        if let value = try? container.decode(Double.self) {
            return value
        }
        if let value = try? container.decode(String.self) {
            return value
        }
        if let value = try? container.decodeNil() {
            if value {
                return JSONNull()
            }
        }
        if var container = try? container.nestedUnkeyedContainer() {
            return try decodeArray(from: &container)
        }
        if var container = try? container.nestedContainer(keyedBy: JSONCodingKey.self) {
            return try decodeDictionary(from: &container)
        }
        throw decodingError(forCodingPath: container.codingPath)
    }

    static func decode(from container: inout KeyedDecodingContainer<JSONCodingKey>, forKey key: JSONCodingKey) throws -> Any {
        if let value = try? container.decode(Bool.self, forKey: key) {
            return value
        }
        if let value = try? container.decode(Int64.self, forKey: key) {
            return value
        }
        if let value = try? container.decode(Double.self, forKey: key) {
            return value
        }
        if let value = try? container.decode(String.self, forKey: key) {
            return value
        }
        if let value = try? container.decodeNil(forKey: key) {
            if value {
                return JSONNull()
            }
        }
        if var container = try? container.nestedUnkeyedContainer(forKey: key) {
            return try decodeArray(from: &container)
        }
        if var container = try? container.nestedContainer(keyedBy: JSONCodingKey.self, forKey: key) {
            return try decodeDictionary(from: &container)
        }
        throw decodingError(forCodingPath: container.codingPath)
    }

    static func decodeArray(from container: inout UnkeyedDecodingContainer) throws -> [Any] {
        var arr: [Any] = []
        while !container.isAtEnd {
            let value = try decode(from: &container)
            arr.append(value)
        }
        return arr
    }

    static func decodeDictionary(from container: inout KeyedDecodingContainer<JSONCodingKey>) throws -> [String: Any] {
        var dict = [String: Any]()
        for key in container.allKeys {
            let value = try decode(from: &container, forKey: key)
            dict[key.stringValue] = value
        }
        return dict
    }

    static func encode(to container: inout UnkeyedEncodingContainer, array: [Any]) throws {
        for value in array {
            if let value = value as? Bool {
                try container.encode(value)
            } else if let value = value as? Int64 {
                try container.encode(value)
            } else if let value = value as? Double {
                try container.encode(value)
            } else if let value = value as? String {
                try container.encode(value)
            } else if value is JSONNull {
                try container.encodeNil()
            } else if let value = value as? [Any] {
                var container = container.nestedUnkeyedContainer()
                try encode(to: &container, array: value)
            } else if let value = value as? [String: Any] {
                var container = container.nestedContainer(keyedBy: JSONCodingKey.self)
                try encode(to: &container, dictionary: value)
            } else {
                throw encodingError(forValue: value, codingPath: container.codingPath)
            }
        }
    }

    static func encode(to container: inout KeyedEncodingContainer<JSONCodingKey>, dictionary: [String: Any]) throws {
        for (key, value) in dictionary {
            let key = JSONCodingKey(stringValue: key)!
            if let value = value as? Bool {
                try container.encode(value, forKey: key)
            } else if let value = value as? Int64 {
                try container.encode(value, forKey: key)
            } else if let value = value as? Double {
                try container.encode(value, forKey: key)
            } else if let value = value as? String {
                try container.encode(value, forKey: key)
            } else if value is JSONNull {
                try container.encodeNil(forKey: key)
            } else if let value = value as? [Any] {
                var container = container.nestedUnkeyedContainer(forKey: key)
                try encode(to: &container, array: value)
            } else if let value = value as? [String: Any] {
                var container = container.nestedContainer(keyedBy: JSONCodingKey.self, forKey: key)
                try encode(to: &container, dictionary: value)
            } else {
                throw encodingError(forValue: value, codingPath: container.codingPath)
            }
        }
    }

    static func encode(to container: inout SingleValueEncodingContainer, value: Any) throws {
        if let value = value as? Bool {
            try container.encode(value)
        } else if let value = value as? Int64 {
            try container.encode(value)
        } else if let value = value as? Double {
            try container.encode(value)
        } else if let value = value as? String {
            try container.encode(value)
        } else if value is JSONNull {
            try container.encodeNil()
        } else {
            throw encodingError(forValue: value, codingPath: container.codingPath)
        }
    }

    public required init(from decoder: Decoder) throws {
        if var arrayContainer = try? decoder.unkeyedContainer() {
            self.value = try JSONAny.decodeArray(from: &arrayContainer)
        } else if var container = try? decoder.container(keyedBy: JSONCodingKey.self) {
            self.value = try JSONAny.decodeDictionary(from: &container)
        } else {
            let container = try decoder.singleValueContainer()
            self.value = try JSONAny.decode(from: container)
        }
    }

    public func encode(to encoder: Encoder) throws {
        if let arr = self.value as? [Any] {
            var container = encoder.unkeyedContainer()
            try JSONAny.encode(to: &container, array: arr)
        } else if let dict = self.value as? [String: Any] {
            var container = encoder.container(keyedBy: JSONCodingKey.self)
            try JSONAny.encode(to: &container, dictionary: dict)
        } else {
            var container = encoder.singleValueContainer()
            try JSONAny.encode(to: &container, value: self.value)
        }
    }
}
