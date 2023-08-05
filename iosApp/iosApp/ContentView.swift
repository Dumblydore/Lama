import SwiftUI
import shared

struct ContentView: View {
    @State
	private var greeting = "Loading"

	var body: some View {
		Text(greeting)
            .onAppear {
                let a = Greeting()
                Task {
                    greeting = try await a.greet()
                }
            }
	}
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
