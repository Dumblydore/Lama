import SwiftUI

struct LoginView: View {
    enum Field {
        case emailAddress
        case password
    }



    @ObservedObject private var viewModel: LoginViewModel
    @FocusState private var focusedField: Field?

    init(viewModel: LoginViewModel) {
        self.viewModel = viewModel
    }

    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                TextField("Enter Username", text: $viewModel.username)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .focused($focusedField, equals: .emailAddress)
                    .textContentType(.emailAddress)
                    .submitLabel(.next)
                SecureField("Enter Password", text: $viewModel.password)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .focused($focusedField, equals: .password)
                    .textContentType(.password)
                    .submitLabel(.send)
                ZStack {
                    Button("Login", action: viewModel.login).disabled(viewModel.enableLogin == false)
                        .buttonStyle(BorderedButtonStyle())
//                        .alert
//                        .alert(isPresented: (viewModel.loginState == .Failure))
                    if (viewModel.loginState == .Loading) {
                        ProgressView()
                    }
                }
            }.padding().onSubmit {
                switch focusedField {
                case .emailAddress:
                    focusedField = .password
                default:
                    focusedField = nil
                    viewModel.login()
                }
            }
        }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView(viewModel: LoginViewModel(router: Router.shared, client: HttpClient.shared))
    }
}
