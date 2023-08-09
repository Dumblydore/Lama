package me.mauricee.lama.di

import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.mauricee.lama.classes.ClassesComponent
import me.mauricee.lama.login.LoginComponent
import me.mauricee.lama.root.start.StartComponent
import me.tatarka.inject.annotations.Provides

interface UiComponent : StartComponent, LoginComponent, ClassesComponent {
    @Provides
    fun provideCircuitConfig(
        uiFactories: Set<Ui.Factory>,
        presenterFactories: Set<Presenter.Factory>,
    ): CircuitConfig = CircuitConfig.Builder()
            .addUiFactories(uiFactories)
            .addPresenterFactories(presenterFactories)
            .build()
}