package me.mauricee.lama.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable

@Composable
fun LamaTheme(
    useDarkColors: Boolean = isSystemInDarkTheme(),
    useDynamicColors: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorScheme(useDarkColors, useDynamicColors),
//        typography = TiviTypography,
//        shapes = TiviShapes,
        content = content,
    )
}
