package me.mauricee.lama.settings

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings
import me.mauricee.lama.core.base.di.ApplicationScope
import me.tatarka.inject.annotations.Provides
import java.util.prefs.Preferences

actual interface PreferencesPlatformComponent {
    @ApplicationScope
    @Provides
    fun provideSettings(delegate: Preferences): ObservableSettings = PreferencesSettings(delegate)
}