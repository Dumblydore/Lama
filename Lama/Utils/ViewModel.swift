import Foundation

protocol ViewModel: ObservableObject {

}

extension ViewModel {
    func updateState(action: @escaping () -> Void) {
        DispatchQueue.main.async {
            action()
        }
    }
}
