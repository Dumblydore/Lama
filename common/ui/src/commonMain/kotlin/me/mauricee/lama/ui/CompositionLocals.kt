package me.mauricee.lama.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.staticCompositionLocalOf
import me.mauricee.lama.common.resource.strings.date.LamaDateFormatter

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass> {
    error("No WindowSizeClass available")
}

val LocalLamaDateFormatter = staticCompositionLocalOf<LamaDateFormatter> {
    error("LamaDateFormatter not provided")
}