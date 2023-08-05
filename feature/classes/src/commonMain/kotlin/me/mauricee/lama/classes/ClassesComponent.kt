package me.mauricee.lama.classes

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

interface  ClassesComponent {
    @IntoSet
    @Provides
    fun bindClassesPresenterFactory(factory: ClassesUiPresenterFactory): Presenter.Factory = factory

    @IntoSet
    @Provides
    fun bindClassesUiFactory(factory: ClassesUiFactory): Ui.Factory = factory

}