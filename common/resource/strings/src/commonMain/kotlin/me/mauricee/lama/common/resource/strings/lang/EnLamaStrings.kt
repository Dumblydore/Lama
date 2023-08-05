package me.mauricee.lama.common.resource.strings.lang

import cafe.adriel.lyricist.LyricistStrings
import me.mauricee.lama.common.resource.strings.HomeStrings
import me.mauricee.lama.common.resource.strings.LamaStrings
import me.mauricee.lama.common.resource.strings.Locales
import me.mauricee.lama.common.resource.strings.LoginStrings
import me.mauricee.lama.common.resource.strings.NavigationItems
import me.mauricee.lama.common.resource.strings.fmt

@LyricistStrings(languageTag = Locales.EN, default = true)
val EnLamaStrings = LamaStrings(
    login = LoginStrings(
        emailLabel = "Email",
        passwordLabel = "Password",
        loginButton = "Login",
        invalidCredentialsMessage = "Invalid credentials",
        unknownErrorMessage = { errorCode -> "Unknown error: %s".fmt(errorCode) },
    ),
    home = HomeStrings(
        navigationItems = NavigationItems(
            classes = "Classes",
            workouts = "Workouts",
            events = "Events",
            notifications = "Notifications",
            about = "About"
        )
    )
)