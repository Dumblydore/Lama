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
            TabView {
                //TODO
                TodayView(viewModel: ReservationListViewModel(personId: session.userId, client: zenClient)).tabItem {
                    Text("Dashboard")
                }
                CalendarView().tabItem {
                    Text("Calendar")
                }
                CalendarView().tabItem {
                    Text("History")
                }
            }
        }
    }
}
