package me.mauricee.lama.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import me.mauricee.lama.core.base.di.ApplicationScope
import me.tatarka.inject.annotations.Provides
import platform.Foundation.NSUserDefaults

actual interface PreferencesPlatformComponent {
    @ApplicationScope
    @Provides
    fun provideSettings(delegate: NSUserDefaults): ObservableSettings =
        NSUserDefaultsSettings(delegate)
}