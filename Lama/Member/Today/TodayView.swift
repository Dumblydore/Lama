//
//  TodayView.swift
//  Lama
//
//  Created by Maurice Edwards on 9/30/21.
//

import SwiftUI

struct TodayView: View {
    private let viewModel: ReservationListViewModel
    init(viewModel: ReservationListViewModel) {
        self.viewModel = viewModel
    }
    var body: some View {
        if(viewModel.isLoading) {
            ProgressView()
        } else if (viewModel.hasError) {
            Text("Error!")
        } else if(viewModel.reservations.isEmpty) {
            Text("No reservations!")
        } else {
            List(viewModel.reservations) {
                Text($0.name)
            }
        }
    }
}
