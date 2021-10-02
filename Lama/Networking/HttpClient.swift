//
//  HttpClient.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation
 
struct HttpClient : Post, Get {
    static let shared = HttpClient()
    private let defaultHeaders = [
        "Content-Type": "application/json",
        "Accept": "application/json"
    ]
    private let decoder = JSONDecoder()
    
    func post<R: Codable, T: Codable>(url: String, body: R, headers: [String:String] = [:]) async throws -> T {
        let url = URL(string: url)!
        var urlRequest = URLRequest(url: url)
        let jsonRequest = try! JSONEncoder().encode(body)
        
        urlRequest.httpMethod = "Post"
        urlRequest.httpBody = jsonRequest
        urlRequest.allHTTPHeaderFields = defaultHeaders.merging(headers) { (_, new) in new }
        
        let (data, _) = try await URLSession.shared.data(for: urlRequest)
        return try decoder.decode(T.self, from: data)
    }
    
    func get<T: Codable>(url: String, headers: [String:String] = [:]) async throws -> T {
        let url = URL(string: url)!
        var urlRequest = URLRequest(url: url)
        
        urlRequest.httpMethod = "Get"
        urlRequest.allHTTPHeaderFields = defaultHeaders.merging(headers) { (_, new) in new }
        
        let (data, _) = try await URLSession.shared.data(for: urlRequest)
        return try decoder.decode(T.self, from: data)
    }
}


protocol Post {
    func post<R: Codable, T: Codable>(url: String, body: R, headers: [String:String]) async throws -> T
}

protocol Get {
    func get<T: Codable>(url: String, headers: [String:String]) async throws -> T
}
