package me.mauricee.lama.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.core.base.util.AppDispatchers
import me.mauricee.lama.settings.PreferencesComponent
import me.mauricee.lama.zen.ZenComponent
import me.tatarka.inject.annotations.Provides

interface SharedApplicationComponent : ZenComponent, CoreComponent

interface CoreComponent : PreferencesComponent {
    @OptIn(ExperimentalCoroutinesApi::class)
    @ApplicationScope
    @Provides
    fun provideCoroutineDispatchers(): AppDispatchers = AppDispatchers(
        io = Dispatchers.IO,
        databaseWrite = Dispatchers.IO.limitedParallelism(1),
        databaseRead = Dispatchers.IO.limitedParallelism(4),
        computation = Dispatchers.Default,
        main = Dispatchers.Main,
    )
}