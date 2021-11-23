//
//  ReservationViewModel.swift
//  Lama
//
//  Created by Maurice Edwards on 9/30/21.
//

import Foundation

class ReservationListViewModel: ViewModel, ObservableObject {
    @Published public private(set) var isLoading: Bool = false
    @Published public private(set) var hasError: Bool = false
    @Published public private(set) var reservations: [ReservationListItem] = []
    @Published public private(set) var name: String = ""

    private let session: Session

    init(session: Session, client: ZenApiClient) {
        self.session = session
        self.name = session.firstName
        Task {
            updateState { self.isLoading = true }
            guard let response = try? await client.getReservations(personId: session.userId) else {
                updateState {
                    self.isLoading = false
                    self.hasError = true
                }
                return
            }
            self.reservations = response.payloadArray.map { ReservationListItem(id: $0.item.itemID, name: $0.item.name) }
        }
    }
}

struct ReservationListItem: Identifiable {
    let id, name: String
}
