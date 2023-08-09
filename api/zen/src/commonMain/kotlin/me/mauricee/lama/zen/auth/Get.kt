package me.mauricee.lama.zen.auth

import me.mauricee.lama.zen.login.LoginResponse

interface GetAuthState {
    operator fun invoke(loginResponse: LoginResponse): AuthState
}