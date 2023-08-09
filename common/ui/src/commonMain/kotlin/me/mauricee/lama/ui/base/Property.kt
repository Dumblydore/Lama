package me.mauricee.lama.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun <T> asyncProperty(
    initialValue: T,
    key: Any = Unit,
    action: suspend () -> T
) : T {
    var state by remember { mutableStateOf(initialValue) }
    LaunchedEffect(key) {
        state = action()
    }
    return state
}

@Composable
fun <T> asyncProperty(action: suspend () -> T) : T? {
    var state by remember { mutableStateOf<T?>(null) }
    LaunchedEffect(Unit) {
        state = action()
    }
    return state
}