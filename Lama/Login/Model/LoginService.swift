//
//  LoginService.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation

extension HttpClient {
    func login(username: String, password: String) async throws -> LoginResponse {
        let request = LoginRequest(username: username, password: password)
        return try await post(url: "https://memberappv220.zenplanner.com/auth/v1/login", body: request)
    }

    func currentUser(authToken: String) async throws -> UserResponse {
        return try await get(url: "https://memberappapiv220.zenplanner.com/elements/api-v2/profiles/current", headers: [
            "Authorization": "Bearer \(authToken)"
        ])
    }
}
