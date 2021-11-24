//
//  ClassService.swift
//  Lama
//
//  Created by Maurice Edwards on 11/23/21.
//

import Foundation
import Combine

extension ZenApiClient {
    func getClassForDates(personId: String, beginDate: Date, endDate: Date) -> AnyPublisher<ClassResponse, Error> {
        let beginDateStr = timestampFormat.string(from: beginDate)
        let endDateStr = timestampFormat.string(from: endDate)
        let request = ClassRequest(personID: personId, beginDate: beginDateStr, endDate: endDateStr)
        return httpClient.post(url: "\(host)/elements/api-v2/calendars/classes", body: request, headers: ["Authorization": "Bearer \(session.token)"])
    }
}
