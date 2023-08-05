package me.mauricee.lama.zen

import io.ktor.client.HttpClient
import me.mauricee.lama.zen.auth.LoginApi
import me.tatarka.inject.annotations.Provides

interface ZenComponent : /*ZenPlatformComponent,*/ ZenCommonComponent

//expect interface ZenPlatformComponent

interface ZenCommonComponent {
    @Provides
    fun provideLoginApi(/*httpClient: HttpClient*/): LoginApi = LoginApi()
}