//
//  ReservationViewModel.swift
//  Lama
//
//  Created by Maurice Edwards on 9/30/21.
//

import Foundation
import Combine

class TodayViewModel: ViewModel {
    @Published public private(set) var isLoading: Bool = false
    @Published public private(set) var hasError: Bool = false
    @Published public private(set) var reservations = [ReservationListItem]()
    @Published public private(set) var workouts: [WorkoutListItem] = []
    @Published public private(set) var name: String = ""

    private let client: ZenApiClient
    private let session: Session
    private let fromFmt = DateFormatter()
    private let toFmt = DateFormatter()
    private var cancellables = [AnyCancellable]()

    init(session: Session, client: ZenApiClient) {
        self.session = session
        self.client = client
        self.name = session.firstName
        self.fromFmt.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSz"
        self.toFmt.locale = Locale(identifier: "en_US_POSIX")
        self.toFmt.dateFormat = "HH:mm a"
    }

    func refresh() {
        let reservations = getReservations().share()
        let workouts = getWorkouts().share()

        reservations
            .receive(on: DispatchQueue.main)
            .assign(to: &$reservations)

        workouts
            .receive(on: DispatchQueue.main)
            .assign(to: &$workouts)

        reservations.combineLatest(workouts)
            .compactMap { (_, _) in false }
            .prepend([true])
            .receive(on: DispatchQueue.main)
            .assign(to: &$isLoading)
    }

    private func getReservations() -> AnyPublisher<[ReservationListItem], Never> {
        client.getReservations(personId: session.userId).map { response in
            return response.payloadArray.map {
                let startTime = self.convertDateToString(date: self.fromFmt.date(from: $0.schedule.start))
                let endTime = self.convertDateToString(date: self.fromFmt.date(from: $0.schedule.end))
                return ReservationListItem(id: $0.item.itemID,
                                           name: $0.item.name,
                                           startTime: startTime,
                                           endTime: endTime)
            }
        }.replaceError(with: []).eraseToAnyPublisher()
    }

    private func getWorkouts() -> AnyPublisher<[WorkoutListItem], Never> {
        let today = Date()
        return client.getPersonalizedWorkouts(personId: session.userId, fromDate: today.startOfDay, toDate: today.dayAfter).map { response in
            guard let workouts = response.workouts.first?.workoutItemInfo.sections else {
                return []
            }
            return workouts.map { workout in
                WorkoutListItem(id: workout.workoutSectionID, name: workout.name, skills: workout.skills.map { $0.text })
            }
        }.replaceError(with: []).eraseToAnyPublisher()
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

struct WorkoutListItem: Identifiable {
    let id, name: String
    let skills: [String]
}
