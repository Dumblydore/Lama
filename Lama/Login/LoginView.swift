import SwiftUI

struct LoginView: View {
    @State private var userName: String = ""
    @State private var password: String = ""
    @State private var result: String = ""
    
    var body: some View {
        VStack(alignment: .center) {
            TextField("Enter Username", text: $userName)
            TextField("Enter Password", text: $password)
            Button("Login") { login() }
            Text("\(result)")
        }
    }
    
    func login() {
        do {
            result = "Loading..."
            let request = LoginRequest(username: userName, password: password)
            let jsonRequest = try! JSONEncoder().encode(request)
            
            let url = URL(string: "https://memberappv220.zenplanner.com/auth/v1/login")!
            var urlRequest = URLRequest(url: url)
            
            urlRequest.httpMethod = "Post"
            urlRequest.httpBody = jsonRequest
            urlRequest.allHTTPHeaderFields = [
                "Content-Type": "application/json",
                "Accept": "application/json"
            ]
            
            let session = URLSession.shared
            let task = session.dataTask(with: urlRequest) { (data, response, error) in
                
                if let error = error {
                    result = "Error! \(error.localizedDescription)"
                } else if let data = data,
                          let json = try? JSONSerialization.jsonObject(with: data, options: []) as? [String: Any] {
                    if let token = json["token"] as? String {
                        result = token
                    } else {
                        result = "Success"
                    }
                }
                else {
                    result = "Unknown Error!"
                }
            }
            task.resume()
        } catch { result = error.localizedDescription }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
