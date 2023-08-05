package me.mauricee.lama.di

import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.mauricee.lama.login.LoginComponent
import me.tatarka.inject.annotations.Provides

interface UiComponent : LoginComponent {
    @Provides
    fun provideCircuitConfig(
        uiFactories: Set<Ui.Factory>,
        presenterFactories: Set<Presenter.Factory>,
    ): CircuitConfig = CircuitConfig.Builder()
            .addUiFactories(uiFactories)
            .addPresenterFactories(presenterFactories)
            .build()
}