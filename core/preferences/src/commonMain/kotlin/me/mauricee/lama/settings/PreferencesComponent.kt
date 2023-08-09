package me.mauricee.lama.settings

import me.mauricee.lama.core.base.di.ApplicationScope
import me.tatarka.inject.annotations.Provides

expect interface PreferencesPlatformComponent

interface PreferencesComponent : PreferencesPlatformComponent {
    @ApplicationScope
    @Provides
    fun providePreferences(bind: LamaPreferencesImpl): LamaPreferences = bind
}
