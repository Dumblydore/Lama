//
//  LamaApp.swift
//  Lama
//
//  Created by Maurice Edwards on 8/16/21.
//

import SwiftUI

@main
struct LamaApp: App {

    private let persistenceController = PersistenceController.shared
    @ObservedObject private var router = Router.shared

    var body: some Scene {
        WindowGroup {
            switch router.route {
            case .login: LoginView(viewModel: LoginViewModel(router: router, client: HttpClient.shared)).environment(\.managedObjectContext, persistenceController.container.viewContext)
            case .member: MemberView().environment(\.managedObjectContext, persistenceController.container.viewContext)
            }
        }
    }
}
