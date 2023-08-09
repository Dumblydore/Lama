package me.mauricee.lama.di

import androidx.compose.ui.unit.Density
import me.mauricee.lama.core.base.app.ApplicationInfo
import me.mauricee.lama.core.base.di.ApplicationScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import platform.Foundation.NSBundle
import platform.Foundation.NSUserDefaults

@Component
@ApplicationScope
abstract class IosApplicationComponent : SharedApplicationComponent {

    @ApplicationScope
    @Provides
    fun provideApplicationId(): ApplicationInfo = ApplicationInfo(
        packageName = NSBundle.mainBundle.bundleIdentifier ?: PackageName,
        debugBuild = Platform.isDebugBinary,
        versionName = NSBundle.mainBundle.infoDictionary
            ?.get("CFBundleShortVersionString") as? String
            ?: "",
        versionCode = (NSBundle.mainBundle.infoDictionary?.get("CFBundleVersion") as? String)
            ?.toIntOrNull()
            ?: 0,
    )

    @Provides
    fun provideNsUserDefaults(): NSUserDefaults = NSUserDefaults.standardUserDefaults

    @Provides
    fun provideDensity(): Density = Density(density = 1f) // FIXME

    companion object {
        private const val PackageName = "me.mauricee.lama"
    }
}
