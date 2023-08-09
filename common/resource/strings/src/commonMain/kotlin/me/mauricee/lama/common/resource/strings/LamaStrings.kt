package me.mauricee.lama.common.resource.strings

import me.mauricee.lama.common.resource.strings.lang.EnLamaStrings

val Strings: Map<String, LamaStrings> = mapOf(
    "en" to EnLamaStrings,
)


object Locales {
    const val EN = "en"
}

data class LamaStrings(
    val login: LoginStrings,
    val home: HomeStrings,
    val defaultError: String,
    val range: (from: String, to: String) -> String
)

/* Login */
data class LoginStrings(
    val emailLabel : String,
    val passwordLabel : String,
    val loginButton : String,
    val invalidCredentialsMessage: String,
    val unknownErrorMessage: (errorCode: String) -> String
)

/* Home */

data class HomeStrings(
    val navigationItems: NavigationItems,
    val classes: Classes
)

data class NavigationItems(
    val classes: String,
    val workouts: String,
    val events: String,
    val notifications: String,
    val about: String
)

/* Classes */
data class Classes(
    val membershipTab: String,
    val reservedTab: String,
    val dropInTab: String,
) {}


expect fun String.fmt(vararg args: Any?): String