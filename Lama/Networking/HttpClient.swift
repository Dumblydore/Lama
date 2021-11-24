//
//  HttpClient.swift
//  Lama
//
//  Created by Maurice Edwards on 9/27/21.
//

import Foundation
import Combine

struct HttpClient: Post, Get {
    static let shared = HttpClient()
    private let defaultHeaders = [
        "Content-Type": "application/json",
        "Accept": "application/json"
    ]
    private let decoder = JSONDecoder()

    func post<R: Codable, T: Codable>(url: String, body: R, headers: [String: String] = [:]) -> AnyPublisher<T, Error> {
        let url = URL(string: url)!
        var urlRequest = URLRequest(url: url)
        let jsonRequest = try! JSONEncoder().encode(body)

        urlRequest.httpMethod = "Post"
        urlRequest.httpBody = jsonRequest
        urlRequest.allHTTPHeaderFields = defaultHeaders.merging(headers) { (_, new) in new }

        return URLSession.shared.dataTaskPublisher(for: urlRequest)
            .map { $0.data }
            .decode(type: T.self, decoder: decoder)
            .eraseToAnyPublisher()
    }

    func get<T: Codable>(url: String, headers: [String: String] = [:]) -> AnyPublisher<T, Error> {
        let url = URL(string: url)!
        var urlRequest = URLRequest(url: url)

        urlRequest.httpMethod = "Get"
        urlRequest.allHTTPHeaderFields = defaultHeaders.merging(headers) { (_, new) in new }

        return URLSession.shared.dataTaskPublisher(for: urlRequest)
            .map { $0.data }
            .decode(type: T.self, decoder: decoder)
            .eraseToAnyPublisher()
    }
}


protocol Post {
    func post<R: Codable, T: Codable>(url: String, body: R, headers: [String: String]) -> AnyPublisher<T, Error>
}

protocol Get {
    func get<T: Codable>(url: String, headers: [String: String]) -> AnyPublisher<T, Error>
}
