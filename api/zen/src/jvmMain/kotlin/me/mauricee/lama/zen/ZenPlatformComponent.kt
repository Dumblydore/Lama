package me.mauricee.lama.zen

import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.zen.auth.AuthStore
import me.mauricee.lama.zen.auth.JvmAuthStore
import me.tatarka.inject.annotations.Provides

actual interface ZenPlatformComponent {
    @ApplicationScope
    @Provides
    fun provideAuthStore(store: JvmAuthStore): AuthStore = store
}