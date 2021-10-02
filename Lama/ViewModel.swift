//
//  ViewModel.swift
//  Lama
//
//  Created by Maurice Edwards on 9/30/21.
//

import Foundation


//protocol HasErrorState : ObservableObject {
//    @Published var hasError: Bool = false
//    @Published var errorMessage: String? = null
//}


protocol HasLoadingState : ObservableObject {
//    @Published public private(set) var isLoading: Bool = false
}


protocol ViewModel {
    
}


extension ViewModel {
    func updateState(action: @escaping () -> Void) {
        DispatchQueue.main.async {
            action()
        }
    }
}
