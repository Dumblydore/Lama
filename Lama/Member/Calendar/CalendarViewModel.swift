//
//  CalendarViewModel.swift
//  Lama
//
//  Created by Maurice Edwards on 11/19/21.
//

import Foundation
import Combine

class CalendarViewModel: ViewModel, ObservableObject {

    private let selectedDatePublisher = CurrentValueSubject<Date, Never>(Date())
    private let session: Session
    private let client: ZenApiClient

    @Published public private(set) var dates: [Date] = []
    @Published public private(set) var selectedDate = Date()
    @Published public private(set) var currentMonth: String
    @Published public private(set) var classes: [ClassListItem] = []
    private let monthFormat = DateFormatter()


    init(session: Session, client: ZenApiClient) {
        self.session = session
        self.client = client

        monthFormat.dateFormat = "MMMM"
        currentMonth = monthFormat.string(from: Date())

        let minDate = Date()
        let maxDate = minDate.endOfYear
        var date = minDate

        while date <= maxDate {
            dates.append(date)
            date = date.dayAfter
        }
    }

    func onDateAppeared(date: Date) {
        self.currentMonth = self.monthFormat.string(from: date)
    }

    func onDateClicked(date: Date) {
        selectedDatePublisher.send(date)
    }

    func refresh() {
        selectedDatePublisher
            .receive(on: DispatchQueue.main)
            .assign(to: &$selectedDate)

        selectedDatePublisher.flatMap(getClassesForDay)
            .replaceError(with: [])
            .receive(on: DispatchQueue.main)
            .assign(to: &$classes)
    }

    private func getClassesForDay(date: Date) -> AnyPublisher<[ClassListItem], Error> {
        return client.getClassForDates(personId: session.userId, beginDate: date, endDate: date.dayAfter)
            .flatMap { $0.classes.publisher }
            .map { classItem in
            ClassListItem(
                id: classItem.item.itemID,
                title: classItem.item.name,
                startTime: classItem.schedule.start,
                endTime: classItem.schedule.end,
                capacity: classItem.capacity.totalSpots,
                enrolled: classItem.capacity.totalSpots - classItem.capacity.remainingSpots
            )
        }.collect().eraseToAnyPublisher()

    }
}

struct ClassListItem: Hashable {
    let id: String
    let title: String
    let startTime: String
    let endTime: String
    let capacity: Int
    let enrolled: Int
}
