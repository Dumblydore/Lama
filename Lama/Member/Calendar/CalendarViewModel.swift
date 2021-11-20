//
//  CalendarViewModel.swift
//  Lama
//
//  Created by Maurice Edwards on 11/19/21.
//

import Foundation

class CalendarViewModel: ViewModel, ObservableObject {
    
    var dates: [Date] = []
    var selectedDate = Date()
    var classes: [Class] = [
        Class(id: UUID().uuidString,title: "Title", startTime: "11:30am",endTime: "12:30pm", gym: "Test Gym", capacity: 10, enrolled: 5),
        Class(id: UUID().uuidString, title: "Title", startTime: "11:30am",endTime: "12:30pm", gym: "Test Gym", capacity: 10, enrolled: 5),
        Class(id: UUID().uuidString, title: "Title", startTime: "11:30am",endTime: "12:30pm", gym: "Test Gym", capacity: 10, enrolled: 5)
    ]
    
    init() {
        let calendar = Calendar(identifier: .gregorian)
        var components = DateComponents()
        components.calendar = calendar

        components.year = -18
        components.month = 12
        let maxDate = calendar.date(byAdding: components, to: selectedDate)!

        components.year = -150
        let fmt = DateFormatter()
        fmt.dateFormat = "dd/MM/yyyy"
        let minDate = calendar.date(byAdding: components, to: selectedDate)!
        var date = minDate
        
        while date <= maxDate {
            print(fmt.string(from: date))
            dates.append(date)
            date = Calendar.current.date(byAdding: .day, value: 1, to: date)!
        }
    }
    
}


struct Class : Hashable {
    let id: String
    let title: String
    let startTime: String
    let endTime: String
    let gym: String
    let capacity: Int
    let enrolled: Int
    
    init(id: String, title: String, startTime: String,endTime: String, gym: String, capacity: Int, enrolled: Int) {
        self.id = id
        self.title = title
        self.startTime = startTime
        self.endTime = endTime
        self.gym = gym
        self.capacity = capacity
        self.enrolled = enrolled
    }
}
