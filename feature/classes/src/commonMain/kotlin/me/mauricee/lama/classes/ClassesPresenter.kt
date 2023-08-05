package me.mauricee.lama.classes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import me.mauricee.lama.ui.base.ClassesScreen
import me.tatarka.inject.annotations.Inject

@Inject
class ClassesUiPresenterFactory(
    private val presenterFactory: (Navigator) -> ClassesPresenter,
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is ClassesScreen -> presenterFactory(navigator)
            else -> null
        }
    }
}

@Inject
class ClassesPresenter() : Presenter<ClassesState> {
    @Composable
    override fun present(): ClassesState {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()

        return ClassesState(
            username = username,
            password = password,
            result = result,
            eventSink = { event -> }
        )
    }

}