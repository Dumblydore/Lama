package me.mauricee.lama.core.base.util

import kotlinx.coroutines.CoroutineDispatcher

data class AppDispatchers(
    val io: CoroutineDispatcher,
    val databaseWrite: CoroutineDispatcher,
    val databaseRead: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val main: CoroutineDispatcher
)