package me.mauricee.lama.android.inject

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.unit.Density
import androidx.preference.PreferenceManager
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings
import me.mauricee.lama.android.BuildConfig
import me.mauricee.lama.android.LamaApplication
import me.mauricee.lama.core.base.app.ApplicationInfo
import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.di.SharedApplicationComponent
import me.mauricee.lama.settings.AppSharedPreferences
import me.mauricee.lama.zen.auth.AndroidAuthStore
import me.mauricee.lama.zen.auth.AuthSharedPreferences
import me.mauricee.lama.zen.auth.AuthStore
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
@ApplicationScope
abstract class AndroidApplicationComponent(
    @get:Provides val application: Application,
) : SharedApplicationComponent {

//    abstract val initializers: AppInitializers
//    abstract val workerFactory: TiviWorkerFactory

    @Suppress("DEPRECATION")
    @ApplicationScope
    @Provides
    fun provideApplicationInfo(application: Application): ApplicationInfo {
        val packageInfo = application.packageManager.getPackageInfo(application.packageName, 0)

        return ApplicationInfo(
            packageName = application.packageName,
            debugBuild = BuildConfig.DEBUG,
            versionName = packageInfo.versionName,
            versionCode = packageInfo.versionCode,
        )
    }

//    @Provides
//    @IntoSet
//    fun provideEmojiInitializer(bind: EmojiInitializer): AppInitializer = bind

    @ApplicationScope
    @Provides
    fun provideDensity(application: Application): Density = Density(application)

    @ApplicationScope
    @Provides
    fun provideAuthSharedPrefs(
        application: Application,
    ): AuthSharedPreferences {
        return application.getSharedPreferences("zen_auth", Context.MODE_PRIVATE)
    }

    @ApplicationScope
    @Provides
    fun provideSettings(delegate: AppSharedPreferences): ObservableSettings {
        return SharedPreferencesSettings(delegate)
    }

    @ApplicationScope
    @Provides
    fun provideAppPreferences(
        context: Application,
    ): AppSharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @ApplicationScope
    @Provides
    fun provideAuthStore(store: AndroidAuthStore): AuthStore = store

    companion object {
        fun from(context: Context): AndroidApplicationComponent {
            return (context.applicationContext as LamaApplication).component
        }
    }
}
