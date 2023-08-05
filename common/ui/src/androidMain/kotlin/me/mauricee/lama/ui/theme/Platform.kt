package me.mauricee.lama.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal actual fun colorScheme(
    useDarkColors: Boolean,
    useDynamicColors: Boolean,
): ColorScheme = when {
    useDarkColors -> LamaDarkColors
    else -> LamaLightColors
}
