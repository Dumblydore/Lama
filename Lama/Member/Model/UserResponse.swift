// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let userResponse = try? newJSONDecoder().decode(UserResponse.self, from: jsonData)

import Foundation

// MARK: - UserResponse
struct UserResponse: Codable {
    let payload: UserPayload
    let totalElements, totalPages: Int
    let last, isLast: Bool
}

// MARK: - UserPayload
struct UserPayload: Codable {
    let partition: UserPartition
    let person: UserPerson
    let userAccount: UserAccount
}

// MARK: - UserPartition
struct UserPartition: Codable {
    let partitionID, schoolName, businessPhone, primaryEmail: String
    let status, address1, city, state: String
    let zipCode, brandColor, locale, currency: String
    let portalPaymentTypes, ccConfiguration, eftConfiguration, skillLabel: String
    let timezone: String
    let socialEnabled, portalCreditCard, portalFinancial, portalPayments: Bool
    let workoutTracking, sugarWODEnabled, memberCurriculumEnabled, isAvsSupported: Bool
    let isElementsSupported: Bool

    enum CodingKeys: String, CodingKey {
        case partitionID = "partitionId"
        case schoolName, businessPhone, primaryEmail, status, address1, city, state, zipCode, brandColor, locale, currency, portalPaymentTypes, ccConfiguration, eftConfiguration, skillLabel, timezone, socialEnabled, portalCreditCard, portalFinancial, portalPayments, workoutTracking, sugarWODEnabled, memberCurriculumEnabled, isAvsSupported, isElementsSupported
    }
}

// MARK: - UserPerson
struct UserPerson: Codable {
    let personID, familyID, firstName, lastName: String
    let primaryEmailInfo: UserPrimaryEmailInfo
    let personUniversalProfile: UserPersonUniversalProfile

    enum CodingKeys: String, CodingKey {
        case personID = "personId"
        case familyID = "familyId"
        case firstName, lastName, primaryEmailInfo, personUniversalProfile
    }
}

// MARK: - UserPersonUniversalProfile
struct UserPersonUniversalProfile: Codable {
    let personUniversalProfileDescription, displayName, photoURL: String
    let isProfilePublic, isPhotographPublic: Bool
    let locale, title, universalProfileID: String
    let sort: Int
    let personUniversalProfileID: String

    enum CodingKeys: String, CodingKey {
        case personUniversalProfileDescription = "description"
        case displayName
        case photoURL = "photoUrl"
        case isProfilePublic, isPhotographPublic, locale, title
        case universalProfileID = "universalProfileId"
        case sort
        case personUniversalProfileID = "personUniversalProfileId"
    }
}

// MARK: - UserPrimaryEmailInfo
struct UserPrimaryEmailInfo: Codable {
    let id, type, subType, contactInformationID: String
    let contactInformation: String

    enum CodingKeys: String, CodingKey {
        case id, type, subType
        case contactInformationID = "contactInformationId"
        case contactInformation
    }
}

// MARK: - UserAccount
struct UserAccount: Codable {
    let userAccountID, personID, accessType, username: String

    enum CodingKeys: String, CodingKey {
        case userAccountID = "userAccountId"
        case personID = "personId"
        case accessType, username
    }
}
