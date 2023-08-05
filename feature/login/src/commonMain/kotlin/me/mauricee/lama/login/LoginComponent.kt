package me.mauricee.lama.login

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

interface  LoginComponent {
    @IntoSet
    @Provides
    fun bindLoginPresenterFactory(factory: LoginUiPresenterFactory): Presenter.Factory = factory

    @IntoSet
    @Provides
    fun bindLoginUiFactory(factory: LoginUiFactory): Ui.Factory = factory

}