import Foundation

struct ZenApiClient {
    private let host = "https://memberappv220.zenplanner.com/elements/api-v2/calendars/reservations"
    private let session: Session
    private let httpClient: HttpClient
    
    init(session: Session, httpClient: HttpClient) {
        self.session = session
        self.httpClient = httpClient
    }

    func getReservations(personId: String) async throws -> ReservationResponse {
        let request = ReservationRequest(personID: personId)
        return try await httpClient.post(url: "", body: request, headers: ["Authorization": "Bearer \(session.token)"])
    }
    
//    func getClassesForDate(personId: String, date: Date) async throws -> 
}
