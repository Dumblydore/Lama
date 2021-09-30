//
//  Routes.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation

final class Router: ObservableObject {
    static let shared = Router()
    
    @Published var route: Route = .login
}

enum Route {
    case login
    case member(LoginResponse)
}
