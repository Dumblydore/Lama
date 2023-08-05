package me.mauricee.lama.classes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.mauricee.lama.ui.base.ClassesScreen
import me.tatarka.inject.annotations.Inject

@Immutable
data class ClassesState(
    val username: String = "",
    val password: String = "",
    val result: String = "",
    val eventSink: (ClassesEvent) -> Unit,
) : CircuitUiState

sealed interface ClassesEvent : CircuitUiEvent {

}


@Inject
class ClassesUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is ClassesScreen -> {
            ui<ClassesState> { state, modifier ->
                ClassesUi(state, modifier)
            }
        }

        else -> null
    }
}

@Composable
internal fun ClassesUi(
    state: ClassesState,
    modifier: Modifier = Modifier,
) {
    val eventSink = state.eventSink
    Box(Modifier.fillMaxSize().background(Color.Blue))
//    ClassesUi(
//        state = state,
//        username = { eventSink(ClassesEvent.UpdateUserName(it)) },
//        password = { eventSink(ClassesEvent.UpdatePassword(it)) },
//        login = { eventSink(ClassesEvent.Classes) },
//        modifier = modifier
//    )
}

//@Composable
//private fun ClassesUi(
//    state: ClassesState,
////    username: (String) -> Unit,
////    password: (String) -> Unit,
////    login: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//  Text("Hello World!")
//}