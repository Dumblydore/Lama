package me.mauricee.lama.root.start

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.mauricee.lama.ui.base.LoginScreen
import me.mauricee.lama.ui.base.StartScreen
import me.tatarka.inject.annotations.Inject

@Inject
class StartUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is StartScreen -> {
            ui<StartState> { state, modifier ->
                StartUI(state, modifier)
            }
        }

        else -> null
    }
}

@Composable
internal fun StartUI(
    state: StartState,
    modifier: Modifier = Modifier,
) = Box(modifier)