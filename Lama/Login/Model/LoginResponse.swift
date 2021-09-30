// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let loginResponse = try? newJSONDecoder().decode(LoginResponse.self, from: jsonData)

import Foundation

// MARK: - LoginResponse
struct LoginResponse: Codable {
    let organizationUsers: [OrganizationUser]
    let token, refreshToken, status: String
}

// MARK: - OrganizationUser
struct OrganizationUser: Codable {
    let user: User
    let organization: Organization
    let roles: [String]
}

// MARK: - Organization
struct Organization: Codable {
    let id, name: String
    let region: Region
}

// MARK: - Region
struct Region: Codable {
    let timezone, locale: String
}

// MARK: - User
struct User: Codable {
    let id, firstName, lastName, username: String
}
