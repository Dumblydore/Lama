//
//  MemberView.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import SwiftUI

struct MemberView: View {
    private let session: Session
    private let zenClient: ZenApiClient
    
    init(session: Session, zenApiClient: ZenApiClient) {
        self.session = session
        self.zenClient = zenApiClient
    }
    var body: some View {
            TabView {
                TodayView(viewModel: TodayViewModel(session: session, client: zenClient)).tabItem {
                    Image(systemName: "doc.text.image")
                    Text("Today")
                }
                CalendarView(viewModel: CalendarViewModel(session: session, client: zenClient)).tabItem {
                    Image(systemName: "calendar")
                    Text("Calendar")
                }
                HistoryView(viewModel: HistoryViewModel(session: session)).tabItem {
                    Image(systemName: "clock.arrow.circlepath")
                    Text("History")
                }
            }
    }
}
