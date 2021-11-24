//
//  CalendarView.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import SwiftUI

struct CalendarView: View {
    @ObservedObject private var viewModel: CalendarViewModel
    let dayFormat = DateFormatter()
    let dayOfWeekFormat = DateFormatter()

    init(viewModel: CalendarViewModel) {
        self.viewModel = viewModel
        dayFormat.dateFormat = "dd"
        dayOfWeekFormat.dateFormat = "E"
    }

    var body: some View {
        NavigationView {
            VStack {
                ScrollView(.horizontal) {
                    ScrollViewReader { scrollView in
                        LazyHStack {
                            ForEach(viewModel.dates, id: \.self) { date in
                                VStack(alignment: .center) {
                                    Text(dayOfWeekFormat.string(from: date))
                                    Text(dayFormat.string(from: date))
                                }.id(date)
                                    .onAppear(perform: { viewModel.onDateAppeared(date: date) })
                                    .onTapGesture { viewModel.onDateClicked(date: date) }
                            }
                        }.onChange(of: viewModel.selectedDate) {
                            value in scrollView.scrollTo(value)
                        }
                    }.fixedSize(horizontal: false, vertical: true)
                }
                Divider()
                ScrollView(.vertical) {
                    LazyVStack {
                        ForEach(viewModel.classes, id: \.self.id) { classItem in
                            ClassView(classItem: classItem)
                            Divider()
                        }
                    }
                }
                Spacer()
            }.navigationTitle("\(viewModel.currentMonth)").navigationBarTitleDisplayMode(.inline)
                .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button("Workouts") {
                        print("Help tapped!")
                    }
                }
            }
        }.onAppear {
            viewModel.refresh()
        }
    }
}

struct ClassView: View {
    private let classItem: ClassListItem
    init(classItem: ClassListItem) {
        self.classItem = classItem
    }

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
//                Text(classItem.).font(.subheadline)
                Text(classItem.title).font(.title)
                HStack {
                    Text("\(classItem.startTime)-\(classItem.endTime)").font(.body)
                    Spacer()
                    Text("\(classItem.enrolled)/\\\(classItem.capacity)")
                }
            }.padding(.horizontal, 20).padding(.vertical, 10)
        }
    }
}
