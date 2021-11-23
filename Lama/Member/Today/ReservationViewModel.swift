//
//  ReservationViewModel.swift
//  Lama
//
//  Created by Maurice Edwards on 9/30/21.
//

import Foundation

class ReservationListViewModel: ViewModel, ObservableObject {
    @Published var isLoading: Bool = false
    @Published var hasError: Bool = false
    @Published var reservations: [ReservationListItem] = []
    @Published var workouts: [String] = []
    @Published var name: String = ""

    private let session: Session
    private let fromFmt = DateFormatter()
    private let toFmt = DateFormatter()

    init(session: Session, client: ZenApiClient) {
        self.session = session
        self.name = session.firstName
        self.fromFmt.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSz"
        self.toFmt.locale = Locale(identifier: "en_US_POSIX")
        self.toFmt.dateFormat = "HH:mm a"
        Task {
            updateState { self.isLoading = true }
            guard let response = try? await client.getReservations(personId: session.userId) else {
                updateState {
                    self.isLoading = false
                    self.hasError = true
                }
                return
            }
            self.reservations = response.payloadArray.map {
                let startTime = convertDateToString(date: fromFmt.date(from: $0.schedule.start))
                let endTime = convertDateToString(date: fromFmt.date(from: $0.schedule.end))
                return ReservationListItem(id: $0.item.itemID,
                                           name: $0.item.name,
                                           startTime: startTime,
                                           endTime: endTime)
            }
        }
    }

    private func convertDateToString(date: Date?) -> String {
        if let unwrapped = date {
            return toFmt.string(from: unwrapped)
        } else {
            return "N/A"
        }

    }
}

struct ReservationListItem: Identifiable {
    let id, name, startTime, endTime: String
}
