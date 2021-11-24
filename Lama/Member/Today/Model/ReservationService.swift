//
//  RequestsClient.swift
//  Lama
//
//  Created by Maurice Edwards on 9/30/21.
//

import Foundation
import Combine

extension ZenApiClient {
    func getReservations(personId: String) -> AnyPublisher<ReservationResponse, Error> {
        let request = ReservationRequest(personId: personId)
        return httpClient.post(url: "\(host)/elements/api-v2/calendars/reservations", body: request, headers: ["Authorization": "Bearer \(session.token)"])
    }
}
