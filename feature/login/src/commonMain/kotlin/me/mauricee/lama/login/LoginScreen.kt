package me.mauricee.lama.login

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.mauricee.lama.common.resource.strings.LoginStrings
import me.mauricee.lama.ui.LocalStrings
import me.mauricee.lama.ui.UIState
import me.mauricee.lama.ui.base.LoginScreen
import me.mauricee.lama.ui.components.BorderlessTextField
import me.mauricee.lama.ui.components.lazy.LazyText
import me.tatarka.inject.annotations.Inject

@Immutable
data class LoginState(
    val username: String = "",
    val password: String = "",
    val enableLogin: Boolean = false,
    val state: UIState = UIState.None,
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        LoginForm(
            state = state,
            strings = strings,
            email = username,
            password = password,
            login = login,
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
        )
    }
}

@Composable
fun LoginForm(
    state: LoginState,
    strings: LoginStrings,
    email: (String) -> Unit,
    password: (String) -> Unit,
    login: () -> Unit,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
    ) {

        BorderlessTextField(
            value = state.username,
            onValueChange = email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            label = { Text(strings.emailLabel) },
            modifier = Modifier.fillMaxWidth()
        )

        BorderlessTextField(
            value = state.password,
            onValueChange = password,
            label = { Text(strings.passwordLabel) },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

//        state.error?.let {
//            Text(
//                text = it,
//                color = CSTheme.colors.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//            Spacer(Modifier.height(16.dp))
//        }

        Button(
            onClick = login,
            enabled = state.enableLogin,
            modifier = Modifier
                .animateContentSize(),
//                .conditional(
//                    condition = isLoading,
//                    ifTrue = { wrapContentWidth() },
//                    ifFalse = { fillMaxWidth() }
//                ),
//            shape = CSTheme.shapes.full,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    4.dp,
                    Alignment.CenterHorizontally
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                LazyText(
                    isLoading = state.state.isLoading(),
                    text = strings.loginButton,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}