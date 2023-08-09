package me.mauricee.lama.root.start

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import me.mauricee.lama.core.base.util.AppDispatchers
import me.mauricee.lama.login.LoginRepository
import me.mauricee.lama.login.ObserveZenAuthState
import me.mauricee.lama.login.ZenAuthState
import me.mauricee.lama.ui.base.ClassesScreen
import me.mauricee.lama.ui.base.LoginScreen
import me.mauricee.lama.ui.base.StartScreen
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

object StartState : CircuitUiState


@Inject
class StartUiPresenterFactory(
    private val presenterFactory: (Navigator) -> StartPresenter,
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is StartScreen -> presenterFactory(navigator)
            else -> null
        }
    }
}

@Inject
class StartPresenter(
    @Assisted private val navigator: Navigator,
    private val zenAuthState: ObserveZenAuthState,
    private val loginRepository: LoginRepository
) : Presenter<StartState> {
    @Composable
    override fun present(): StartState {
        LaunchedEffect(Unit) {
            try {
                val screen = if (loginRepository.getAuthState()?.isAuthorized == true) {
                    ClassesScreen
                } else {
                    LoginScreen
                }
                navigator.goTo(screen)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        return StartState
    }

}