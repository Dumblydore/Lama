// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let workoutResponse = try? newJSONDecoder().decode(WorkoutResponse.self, from: jsonData)

import Foundation

// MARK: - WorkoutResponse
struct WorkoutResponse: Codable {
    let workouts: [Workout]
}

// MARK: - Workout
struct Workout: Codable {
    let workoutItemInfo: WorkoutItemInfo
    let eligibleClassInfo: WorkoutEligibleClassInfo
    let isPriority: Bool
}

// MARK: - WorkoutEligibleClassInfo
struct WorkoutEligibleClassInfo: Codable {
    let appointmentID, name, beginDate: JSONNull?

    enum CodingKeys: String, CodingKey {
        case appointmentID = "appointmentId"
        case name, beginDate
    }
}

// MARK: - WorkoutItemInfo
struct WorkoutItemInfo: Codable {
    let item: WorkoutItem
    let sections: [WorkoutSection]
}

// MARK: - WorkoutItem
struct WorkoutItem: Codable {
    let workoutID, name, programDate: String

    enum CodingKeys: String, CodingKey {
        case workoutID = "workoutId"
        case name, programDate
    }
}

// MARK: - WorkoutSection
struct WorkoutSection: Codable {
    let workoutSectionID, name: String
    let sort: Int
    let skills: [WorkoutSkill]

    enum CodingKeys: String, CodingKey {
        case workoutSectionID = "workoutSectionId"
        case name, sort, skills
    }
}

// MARK: - WorkoutSkill
struct WorkoutSkill: Codable {
    let workoutSectionSkillID: String
    let skillID, name, skillDescription: JSONNull?
    let text: String
    let resourceURL, success: JSONNull?
    let sort: Int
    let measurement: JSONNull?
    let items: [JSONAny]

    enum CodingKeys: String, CodingKey {
        case workoutSectionSkillID = "workoutSectionSkillId"
        case skillID = "skillId"
        case name
        case skillDescription = "description"
        case text
        case resourceURL = "resourceUrl"
        case success, sort, measurement, items
    }
}
