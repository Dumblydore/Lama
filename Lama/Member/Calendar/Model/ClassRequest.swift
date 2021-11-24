// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let classRequest = try? newJSONDecoder().decode(ClassRequest.self, from: jsonData)

import Foundation

// MARK: - ClassRequest
struct ClassRequest: Codable {
    let personID, beginDate, endDate: String

    enum CodingKeys: String, CodingKey {
        case personID = "personId"
        case beginDate, endDate
    }
}
