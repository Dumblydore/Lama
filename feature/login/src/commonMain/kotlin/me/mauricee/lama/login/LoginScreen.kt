package me.mauricee.lama.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.mauricee.lama.ui.LocalStrings
import me.mauricee.lama.ui.base.LoginScreen
import me.tatarka.inject.annotations.Inject

@Immutable
data class LoginState(
    val username: String = "",
    val password: String = "",
    val result: String = "",
    val eventSink: (LoginEvent) -> Unit,
) : CircuitUiState

sealed interface LoginEvent : CircuitUiEvent {
    data class UpdateUserName(val username: String) : LoginEvent
    data class UpdatePassword(val password: String) : LoginEvent
    object Login : LoginEvent
}


@Inject
class LoginUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is LoginScreen -> {
            ui<LoginState> { state, modifier ->
                LoginUi(state, modifier)
            }
        }

        else -> null
    }
}

@Composable
internal fun LoginUi(
    state: LoginState,
    modifier: Modifier = Modifier,
) {
    val eventSink = state.eventSink
    LoginUi(
        state = state,
        username = { eventSink(LoginEvent.UpdateUserName(it)) },
        password = { eventSink(LoginEvent.UpdatePassword(it)) },
        login = { eventSink(LoginEvent.Login) },
        modifier = modifier
    )
}

@Composable
private fun LoginUi(
    state: LoginState,
    username: (String) -> Unit,
    password: (String) -> Unit,
    login: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalStrings.current.login
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TextField(
            value = state.username,
            onValueChange = username,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            label = { Text(strings.emailLabel) },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.password,
            onValueChange = password,
            label = { Text(strings.passwordLabel) },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = remember { PasswordVisualTransformation() },
            modifier = Modifier.fillMaxWidth()
        )

        TextButton(onClick = login) {
            Text(strings.loginButton)
        }
    }
}