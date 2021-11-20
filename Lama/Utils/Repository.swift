//
//  Repository.swift
//  Lama
//
//  Created by Maurice Edwards on 10/2/21.
//

import Foundation


class Fetcher<K,V> {
    
}

class Cache<K,R,V> {
//    func get(key: K) -> V {
//        throw Exrror
//    }
    
    func write(key: K, raw: R) async {
        
    }
}

//class Repository<K: Hashable, V>  {
//    private let items: [K: RepositoryItem<V>] = [:]
//    init() {
//
//    }
//
//    func get(key: K) -> V {
//
//    }
//}
//
//class RepositoryItem<K, V> {
//    @Published let content: V
//    private let cache: Cache
//    private let fetcher: Fetcher
//
//    init(
//        cache: Cache,
//        fetcher: Fetcher
//    ) {
//        self.cache = cache
//        Task {
//            content = cache.
//        }
//    }
//}
