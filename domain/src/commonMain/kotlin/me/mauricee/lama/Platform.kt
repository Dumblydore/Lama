package me.mauricee.lama

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform