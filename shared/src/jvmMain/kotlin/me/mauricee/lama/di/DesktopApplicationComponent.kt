package me.mauricee.lama.di

import androidx.compose.ui.unit.Density
import me.mauricee.lama.core.base.app.ApplicationInfo
import me.mauricee.lama.core.base.di.ApplicationScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.prefs.Preferences

@Component
@ApplicationScope
abstract class DesktopApplicationComponent : SharedApplicationComponent {

    @ApplicationScope
    @Provides
    fun provideApplicationId(): ApplicationInfo = ApplicationInfo(
        packageName = PackageName,
        debugBuild = true,
        versionName = "1.0.0",
        versionCode = 1,
    )

    @ApplicationScope
    @Provides
    fun providePreferences(): Preferences = Preferences.userRoot().node(PackageName)

    @Provides
    fun provideDensity(): Density = Density(density = 1f) // FIXME

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(
        // interceptors: Set<Interceptor>,
    ): OkHttpClient = OkHttpClient.Builder()
        // .apply { interceptors.forEach(::addInterceptor) }
        // Adjust the Connection pool to account for historical use of 3 separate clients
        // but reduce the keepAlive to 2 minutes to avoid keeping radio open.
        .connectionPool(ConnectionPool(10, 2, TimeUnit.MINUTES))
        .dispatcher(
            Dispatcher().apply {
                // Allow for increased number of concurrent image fetches on same host
                maxRequestsPerHost = 10
            },
        )
        // Increase timeouts
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

    companion object {
        private const val PackageName = "me.mauricee.lama"
    }
}
