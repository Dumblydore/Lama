//
//  LoginService.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation

extension Post {
    func login(username: String, password: String) async throws -> LoginResponse {
        let request = LoginRequest(username: username, password: password)
        return try await HttpClient.shared.post(url: "https://memberappv220.zenplanner.com/auth/v1/login", body: request)
    }
}
