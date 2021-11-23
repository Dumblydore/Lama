import Foundation

struct ZenApiClient {
    let host = "https://memberappv220.zenplanner.com/elements/api-v2/calendars/reservations"
    let session: Session
    let httpClient: HttpClient
    
    init(session: Session, httpClient: HttpClient) {
        self.session = session
        self.httpClient = httpClient
    }
    
//    func getClassesForDate(personId: String, date: Date) async throws -> 
}
