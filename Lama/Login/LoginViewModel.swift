//
//  LoginViewModel.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation
import LocalAuthentication
import Combine

final class LoginViewModel: ViewModel {
    private let router: Router
    private let client: HttpClient
    private var context = LAContext()
    private var cancellables = [AnyCancellable]()

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
    @Published public private(set) var biometricOption: BiometricLoginButton = .None
    @Published public private(set) var enableLogin: Bool = false
    @Published public private(set) var loginState: LoginState = .None

    init (router: Router, client: HttpClient) {
        self.router = router
        self.client = client
        context.localizedCancelTitle = "Enter Email/Password"
        switch context.biometryType {
        case .faceID: self.biometricOption = .FaceID
        case .touchID: self.biometricOption = .TouchID
        default: self.biometricOption = .None
        }
    }

    func login() {
        loginState = .Loading
        client.login(username: username, password: password)
            .flatMap(fetchSession)
            .eraseToAnyPublisher()
            .sink(receiveCompletion: { _ in self.updateState { self.loginState = .Failure } },
                  receiveValue: { session in self.updateState {
                      self.loginState = .Success
                      self.router.route = .member(session)
                  } }
            ).store(in: &cancellables)
    }

    private func updateEnableLogin() {
        enableLogin = !(username.isEmpty || password.isEmpty)
    }

    private func fetchSession(result: LoginResponse) -> AnyPublisher<Session, Error> {
        return client.currentUser(authToken: result.token)
            .map { $0.payload }
            .map { user in
            Session(token: result.token, refreshToken: result.refreshToken,
                    partitionId: user.partition.partitionID,
                    userId: user.person.personID,
                    firstName: user.person.firstName,
                    lastName: user.person.lastName,
                    username: user.userAccount.username,
                    photoId: user.person.personUniversalProfile.photoURL)
        }.eraseToAnyPublisher()
    }
}


enum LoginState {
    case None
    case Loading
    case Success
    case Failure
}

enum BiometricLoginButton {
    case None
    case FaceID
    case TouchID
}
