import Foundation

protocol ViewModel {

}

extension ViewModel {
    func updateState(action: @escaping () -> Void) {
        DispatchQueue.main.async {
            action()
        }
    }
}
