//
//  LoginService.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation
import Combine

extension HttpClient {
    func login(username: String, password: String) -> AnyPublisher<LoginResponse, Error> {
        let request = LoginRequest(username: username, password: password)
        return post(url: "https://memberappv220.zenplanner.com/auth/v1/login", body: request)
    }

    func currentUser(authToken: String) -> AnyPublisher<UserResponse, Error> {
        return get(url: "https://memberappapiv220.zenplanner.com/elements/api-v2/profiles/current", headers: [
            "Authorization": "Bearer \(authToken)"
        ])
    }
}
