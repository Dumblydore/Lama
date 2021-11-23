//
//  RequestsClient.swift
//  Lama
//
//  Created by Maurice Edwards on 9/30/21.
//

import Foundation

extension ZenApiClient {
    func getReservations(personId: String) async throws -> ReservationResponse {
        let request = ReservationRequest(personId: personId)
        return try await httpClient.post(url: host, body: request, headers: ["Authorization": "Bearer \(session.token)"])
    }
}
