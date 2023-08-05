package me.mauricee.lama.android

import android.app.Application
import me.mauricee.lama.android.inject.AndroidApplicationComponent
import me.mauricee.lama.android.inject.create

class LamaApplication : Application() {
    val component: AndroidApplicationComponent by unsafeLazy {
        AndroidApplicationComponent::class.create(this)
    }
}


@Suppress("NOTHING_TO_INLINE")
inline fun <T> unsafeLazy(noinline initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)