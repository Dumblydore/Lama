//
//  DateExt.swift
//  Lama
//
//  Created by Maurice Edwards on 11/23/21.
//

import Foundation

extension Date {
    static var yesterday: Date { return Date().dayBefore }
    static var tomorrow:  Date { return Date().dayAfter }
    var dayBefore: Date {
        return Calendar.current.date(byAdding: .day, value: -1, to: startOfDay)!
    }
    var dayAfter: Date {
        return Calendar.current.date(byAdding: .day, value: 1, to: startOfDay)!
    }
    var startOfDay: Date {
        return Calendar.current.date(bySettingHour: 0, minute: 0, second: 0, of: self)!
    }
    var endOfYear: Date {
        return Calendar.current.date(from: DateComponents(year: self.year + 1, month: 1, day: 1))!.dayBefore
    }
    var noon: Date {
        return Calendar.current.date(bySettingHour: 12, minute: 0, second: 0, of: self)!
    }
    var month: Int {
        return Calendar.current.component(.month,  from: self)
    }
    var year: Int {
        return Calendar.current.component(.year,  from: self)
    }
    var isLastDayOfMonth: Bool {
        return dayAfter.month != month
    }
}
