import SwiftUI

struct LoginView: View {
    @ObservedObject var viewModel: LoginViewModel
    
    init(viewModel: LoginViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                TextField("Enter Username", text: $viewModel.username).textFieldStyle(RoundedBorderTextFieldStyle())
                SecureField("Enter Password", text: $viewModel.password).textFieldStyle(RoundedBorderTextFieldStyle())
                ZStack {
                    Button("Login", action: viewModel.login).disabled(viewModel.enableLogin == false)
                        .buttonStyle(BorderedButtonStyle())
//                        .alert(isPresented: (viewModel.loginState == .Failure))
                    if (viewModel.loginState == .Loading) {
                        ProgressView()
                    }
                }
            }.padding()
        }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView(viewModel: LoginViewModel(router: Router.shared, client: HttpClient.shared))
    }
}
