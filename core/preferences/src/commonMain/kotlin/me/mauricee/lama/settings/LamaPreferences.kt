package me.mauricee.lama.settings

import kotlinx.coroutines.flow.Flow

interface LamaPreferences {

    var theme: Theme
    fun observeTheme(): Flow<Theme>

    var useDynamicColors: Boolean
    fun observeUseDynamicColors(): Flow<Boolean>

    var useLessData: Boolean
    fun observeUseLessData(): Flow<Boolean>
//
//    var libraryFollowedActive: Boolean
//    fun observeLibraryFollowedActive(): Flow<Boolean>
//
//    var libraryWatchedActive: Boolean
//    fun observeLibraryWatchedActive(): Flow<Boolean>
//
//    var upNextFollowedOnly: Boolean
//    fun observeUpNextFollowedOnly(): Flow<Boolean>

    enum class Theme {
        LIGHT,
        DARK,
        SYSTEM,
    }
}
