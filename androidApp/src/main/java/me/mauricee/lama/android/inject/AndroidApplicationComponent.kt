package me.mauricee.lama.android.inject

import android.app.Application
import android.content.Context
import androidx.compose.ui.unit.Density
import me.mauricee.lama.android.LamaApplication
import me.mauricee.lama.di.SharedApplicationComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

@Component
////@ApplicationScope
abstract class AndroidApplicationComponent(
    @get:Provides val application: Application,
) : SharedApplicationComponent {

//    abstract val initializers: AppInitializers
//    abstract val workerFactory: TiviWorkerFactory

//    @Suppress("DEPRECATION")
////    @ApplicationScope
//    @Provides
//    fun provideApplicationInfo(application: Application): ApplicationInfo {
//        val packageInfo = application.packageManager.getPackageInfo(application.packageName, 0)
//
//        return ApplicationInfo(
//            packageName = application.packageName,
//            debugBuild = BuildConfig.DEBUG,
//            flavor = when (BuildConfig.FLAVOR) {
//                "qa" -> Flavor.Qa
//                else -> Flavor.Standard
//            },
//            versionName = packageInfo.versionName,
//            versionCode = packageInfo.versionCode,
//        )
//    }

//    @Provides
//    @IntoSet
//    fun provideEmojiInitializer(bind: EmojiInitializer): AppInitializer = bind

//    @ApplicationScope
    @Provides
    fun provideOkHttpClient(
        context: Application,
        interceptors: Set<Interceptor>,
    ): OkHttpClient = OkHttpClient.Builder()
        .apply { interceptors.forEach(::addInterceptor) }
        // Around 4¢ worth of storage in 2020
        .cache(Cache(File(context.cacheDir, "api_cache"), 50L * 1024 * 1024))
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

    @Provides
    fun provideDensity(application: Application): Density = Density(application)

    companion object {
        fun from(context: Context): AndroidApplicationComponent {
            return (context.applicationContext as LamaApplication).component
        }
    }
}
