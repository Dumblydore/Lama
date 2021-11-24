//
//  TodayView.swift
//  Lama
//
//  Created by Maurice Edwards on 9/30/21.
//

import SwiftUI

struct TodayView: View {
    @ObservedObject private var viewModel: TodayViewModel
    init(viewModel: TodayViewModel) {
        self.viewModel = viewModel
    }
    var body: some View {
        NavigationView {
            ZStack {
                if(viewModel.isLoading) {
                    ProgressView()
                } else if (viewModel.hasError) {
                    Text("Error!")
                } else {
                    ScrollView(.vertical) {
                        VStack {
                            Text("Reservations").font(.headline).padding(.vertical, 10)
                            ForEach(viewModel.reservations) { reservation in
                                VStack(alignment: .leading) {
                                    Text("\(reservation.startTime)-\(reservation.endTime)").font(.subheadline)
                                    Text(reservation.name).font(.title)
                                }.padding(.horizontal, 10).padding(.vertical, 5)
                            }
                            Text("Workouts").font(.headline).padding(.vertical, 10)
                            ForEach(viewModel.workouts) { workout in
                                VStack(alignment: .leading) {
                                    Text(workout.name)
                                    ForEach(workout.skills, id: \.self) { skill in
                                        Text(skill)
                                    }
                                }
                            }
                        }
                    }
                }
            }.navigationTitle("Hello, \(viewModel.name)").navigationBarTitleDisplayMode(.large)
        }.onAppear(perform: { viewModel.refresh() })
    }
}
