package me.mauricee.lama.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import me.mauricee.lama.common.resource.strings.LamaStrings
import me.mauricee.lama.common.resource.strings.Strings
import me.mauricee.lama.common.resource.strings.lang.EnLamaStrings

val LocalStrings: ProvidableCompositionLocal<LamaStrings> = compositionLocalOf { EnLamaStrings }

@Composable
fun rememberStrings(
    languageTag: LanguageTag = "en",
): Lyricist<LamaStrings> = cafe.adriel.lyricist.rememberStrings(Strings, languageTag)

@Composable
fun ProvideStrings(
    lyricist: Lyricist<LamaStrings> = rememberStrings(),
    content: @Composable () -> Unit,
) {
    cafe.adriel.lyricist.ProvideStrings(lyricist, LocalStrings, content)
}
