package me.mauricee.lama.zen

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header

fun HttpRequestBuilder.requiresAuth() {
    header(RequiresAuthKey, "true")
    refresh()
}

fun HttpRequestBuilder.refresh() {
    header(RequiresRefreshKey, "true")
}

const val RequiresAuthKey = "Requires-Auth"
const val RequiresRefreshKey = "Requires-Refresh"