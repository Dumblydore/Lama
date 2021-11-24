//
//  WorkoutService.swift
//  Lama
//
//  Created by Maurice Edwards on 11/23/21.
//

import Foundation
import Combine

extension ZenApiClient {
    func getPersonalizedWorkouts(personId: String, fromDate: Date, toDate: Date) -> AnyPublisher<WorkoutResponse, Error> {

        let url = "\(host)/elements/api-v2/workouts/prioritized?personId=\(personId)&beginDate=\(timestampFormat.string(from: fromDate))&endDate=\(timestampFormat.string(from: toDate))&_=\(Date().timeStamp)"

        return httpClient.get(url: url, headers: ["Authorization": "Bearer \(session.token)"])
    }
}
