package me.mauricee.lama.login

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.mauricee.lama.core.base.di.ActivityScope
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

interface LoginComponent {
    @IntoSet
    @Provides
    @ActivityScope
    fun bindLoginPresenterFactory(factory: LoginUiPresenterFactory): Presenter.Factory = factory

    @IntoSet
    @Provides
    @ActivityScope
    fun bindLoginUiFactory(factory: LoginUiFactory): Ui.Factory = factory

}