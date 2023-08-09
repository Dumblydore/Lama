package me.mauricee.lama.root.start

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.mauricee.lama.core.base.di.ActivityScope
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

interface StartComponent {
    @IntoSet
    @Provides
    @ActivityScope
    fun bindStartPresenterFactory(factory: StartUiPresenterFactory): Presenter.Factory = factory

    @IntoSet
    @Provides
    @ActivityScope
    fun bindStartUiFactory(factory: StartUiFactory): Ui.Factory = factory

}