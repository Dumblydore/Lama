package me.mauricee.lama.classes

import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.mauricee.lama.zen.management.calendar.ClassesApi
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

interface  ClassesComponent {
    val classesApi: ClassesApi

    @IntoSet
    @Provides
    fun bindClassesPresenterFactory(factory: ClassesUiPresenterFactory): Presenter.Factory = factory

    @IntoSet
    @Provides
    fun bindClassesUiFactory(factory: ClassesUiFactory): Ui.Factory = factory

}