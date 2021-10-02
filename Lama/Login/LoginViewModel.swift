//
//  LoginViewModel.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation

final class LoginViewModel: ObservableObject {
    private let router: Router
    private let client: HttpClient

    @Published var username: String = "" {
        didSet {
            updateEnableLogin()
        }
    }

    @Published var password: String = "" {
        didSet {
            updateEnableLogin()
        }
    }
    @Published var enableLogin: Bool = false
    @Published var loginState: LoginState = .None

    init (router: Router, client: HttpClient) {
        self.router = router
        self.client = client
    }

    func login() {
        loginState = .Loading
        Task {
            do {
                let result = try await client.login(username: username, password: password)
                let user = try await client.currentUser(authToken: result.token).payload
                let session = Session(token: result.token, refreshToken: result.refreshToken,
                                      partitionId: user.partition.partitionID,
                                      userId: user.person.personID,
                                      firstName: user.person.firstName,
                                      lastName: user.person.lastName,
                                      username: user.userAccount.username,
                                      photoId: user.person.personUniversalProfile.photoURL)
                DispatchQueue.main.async {
                    self.loginState = .Success
                    self.router.route = .member(session)
                }
            } catch {
                print("\(error)")
                DispatchQueue.main.async {
                    self.loginState = .Failure
                }
            }
        }
    }

    private func updateEnableLogin() {
        enableLogin = !(username.isEmpty || password.isEmpty)
    }
}


enum LoginState {
    case None
    case Loading
    case Success
    case Failure
}
