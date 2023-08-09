package me.mauricee.lama.data.db

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform