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
        VStack {
            HStack {
                Text(session.username)
            }.foregroundColor(.black)
                .padding()
                .shadow(radius: 5)
                .background(.white)
            let calendarViewModel = CalendarViewModel()
            TabView {
                //TODO
                TodayView(viewModel: ReservationListViewModel(personId: session.userId, client: zenClient)).tabItem {
                    Text("Dashboard")
                }
                CalendarView(viewModel: calendarViewModel).tabItem {
                    Text("Calendar")
                }
                CalendarView(viewModel: calendarViewModel).tabItem {
                    Text("History")
                }
            }
        }
    }
}
