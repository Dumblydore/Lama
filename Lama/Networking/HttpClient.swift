//
//  HttpClient.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation
 
struct HttpClient : Post {
    static let shared = HttpClient()
    private let defaultHeaders = [
        "Content-Type": "application/json",
        "Accept": "application/json"
    ]
    
    func post<R: Codable, T: Codable>(url: String, body: R) async throws -> T {
        let url = URL(string: url)!
        var urlRequest = URLRequest(url: url)
        let jsonRequest = try! JSONEncoder().encode(body)
        
        urlRequest.httpMethod = "Post"
        urlRequest.httpBody = jsonRequest
        urlRequest.allHTTPHeaderFields = defaultHeaders
        
        let (data, _) = try await URLSession.shared.data(for: urlRequest)
        return try JSONDecoder().decode(T.self, from: data)
    }
}


protocol Post {
    func post<R: Codable, T: Codable>(url: String, body: R) async throws -> T
}

protocol Get {
    func post<T: Codable>(url: String) async throws -> T
}
