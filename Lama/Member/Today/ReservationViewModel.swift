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
    @Published public private(set) var reservations: [ReservationListItem] = []

    private let personId: String

    init(personId: String, client: ZenApiClient) {
        self.personId = personId
        Task {
            updateState { self.isLoading = true }
            guard let response = try? await client.getReservations(personId: personId) else {
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
