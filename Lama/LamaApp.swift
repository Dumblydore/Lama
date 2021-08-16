//
//  LamaApp.swift
//  Lama
//
//  Created by Maurice Edwards on 8/16/21.
//

import SwiftUI

@main
struct LamaApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
