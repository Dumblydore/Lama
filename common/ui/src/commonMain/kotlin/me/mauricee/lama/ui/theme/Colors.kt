package me.mauricee.lama.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val paleGray = Color(0xFFF2F3F5)

internal val Azul200 = Color(0xFF9DBFF1)
internal val Azul600 = Color(0xFF1F6DDE)
internal val Azul800 = Color(0xFF0B48A1)

internal val Orange500 = Color(0xFFF9AA33)
internal val Orange700 = Color(0xFFC78522)

internal val Green200 = Color(0xFFF2FDF6)
internal val Green600 = Color(0xFF00D97F)
internal val Green800 = Color(0xFF008545)

internal val Red200 = Color(0xFFFDA3BD)
internal val Red600 = Color(0xFFD9234C)
internal val Red800 = Color(0xFF6F1322)
val LamaLightColors = lightColorScheme(
    background = paleGray,
    onBackground = Color.Black,
    primary = Azul600,
    onPrimary = Color.White,
    primaryContainer = Azul200,
    onPrimaryContainer = Azul800,
    inversePrimary = Azul600,
    secondary = Orange700,
    onSecondary = Color.Black,
//    secondaryContainer = ColorLightTokens.SecondaryContainer,
//    onSecondaryContainer = ColorLightTokens.OnSecondaryContainer,
    tertiary = Green600,
    onTertiary = Color.Black,
    tertiaryContainer = Green200,
    onTertiaryContainer = Green800,
    surface = Color.White,
    onSurface = Color.Black,
//    surfaceVariant = ColorLightTokens.SurfaceVariant,
//    onSurfaceVariant = ColorLightTokens.OnSurfaceVariant,
//    inverseSurface = ColorLightTokens.InverseSurface,
//    inverseOnSurface = ColorLightTokens.InverseOnSurface,
    error = Red600,
    onError = Color.White,
    errorContainer = Red200,
    onErrorContainer = Red800,
//    outline = ColorLightTokens.Outline,
//    outlineVariant = ColorLightTokens.OutlineVariant,
    scrim = Azul200,
)

val LamaDarkColors = darkColorScheme(
    primary = Azul200,
    onPrimary = Color.Black,
    secondary = Orange500,
    onSecondary = Color.Black,
)
