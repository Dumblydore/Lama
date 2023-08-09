package me.mauricee.lama.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Immutable
sealed class UIState {
    data object None : UIState()

    data object Loading : UIState()

    data object Success : UIState()

    fun isLoading(): Boolean = this is Loading

    fun isError(): Boolean = this is Error

    fun isSuccess(): Boolean = this is Success

    fun asError(): Error? = this as? Error

    data class Error(
        val throwable: Throwable? = null,
        val message: @Composable () -> String? = { throwable?.message },
    ) : UIState()
}

@Composable
fun uiState(initial: UIState = UIState.None): MutableState<UIState> =
    remember { mutableStateOf(initial) }

fun Result<*>.asUiState(): UIState = if (isSuccess) {
    UIState.Success
} else {
    UIState.Error(exceptionOrNull())
}