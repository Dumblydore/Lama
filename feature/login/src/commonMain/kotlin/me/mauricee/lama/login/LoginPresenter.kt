package me.mauricee.lama.login

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
import kotlinx.coroutines.launch
import me.mauricee.lama.ui.LocalStrings
import me.mauricee.lama.ui.UIState
import me.mauricee.lama.ui.base.ClassesScreen
import me.mauricee.lama.ui.base.LoginScreen
import me.mauricee.lama.ui.uiState
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LoginUiPresenterFactory(
    private val presenterFactory: (Navigator) -> LoginPresenter,
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is LoginScreen -> presenterFactory(navigator)
            else -> null
        }
    }
}

@Inject
class LoginPresenter(
    @Assisted private val navigator: Navigator,
    private val loginRepository: LoginRepository,
) : Presenter<LoginState> {
    @Composable
    override fun present(): LoginState {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var uiState by uiState()
        val enableLoginButton = remember(username, password) {
            username.isNotBlank() && password.isNotBlank()
        }
        val scope = rememberCoroutineScope()

        return LoginState(
            username = username,
            password = password,
            state = uiState,
            enableLogin = enableLoginButton,
            eventSink = { event ->
                when (event) {
                    LoginEvent.Login -> scope.launch {
                        uiState = UIState.Loading
                        uiState = when (val result = loginRepository.login(username, password)) {
                            is LoginResult.Success -> {
                                navigator.goTo(ClassesScreen)
                                UIState.None
                            }

                            is LoginResult.Error -> UIState.Error { result.error.message }
                            LoginResult.IncorrectCredentials -> UIState.Error { LocalStrings.current.login.invalidCredentialsMessage }
                        }
                    }

                    is LoginEvent.UpdateUserName -> username = event.username
                    is LoginEvent.UpdatePassword -> password = event.password
                }
            }
        )
    }

}