import Foundation

struct ZenApiClient {
    let host = "https://memberappv220.zenplanner.com"
    let session: Session
    let httpClient: HttpClient
    let timestampFormat: DateFormatter = DateFormatter()
    
    init(session: Session, httpClient: HttpClient) {
        self.session = session
        self.httpClient = httpClient
        timestampFormat.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }
}


extension Date {
    var timeStamp: Int64 {
        Int64((self.timeIntervalSince1970 * 1000.0).rounded())
    }
}
