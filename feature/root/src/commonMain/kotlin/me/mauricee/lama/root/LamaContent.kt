package me.mauricee.lama.root

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.screen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import me.mauricee.lama.common.resource.strings.date.LamaDateFormatter
import me.mauricee.lama.root.home.Home
import me.mauricee.lama.root.home.RootViewModel
import me.mauricee.lama.settings.LamaPreferences
import me.mauricee.lama.ui.LocalLamaDateFormatter
import me.mauricee.lama.ui.LocalWindowSizeClass
import me.mauricee.lama.ui.ProvideStrings
import me.mauricee.lama.ui.base.ClassesScreen
import me.mauricee.lama.ui.base.LamaScreen
import me.mauricee.lama.ui.base.LoginScreen
import me.mauricee.lama.ui.theme.LamaTheme
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

typealias LamaContent = @Composable (
    backstack: SaveableBackStack,
    navigator: Navigator,
    modifier: Modifier,
) -> Unit

@Inject
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LamaContent(
    @Assisted backstack: SaveableBackStack,
    @Assisted navigator: Navigator,
    rootViewModel: (CoroutineScope) -> RootViewModel,
    circuitConfig: CircuitConfig,
//    analytics: Analytics,
    lamaDateFormatter: LamaDateFormatter,
//    imageLoader: ImageLoader,
    preferences: LamaPreferences,
    @Assisted modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val root = remember { rootViewModel(coroutineScope) }

    val tiviNavigator: Navigator = remember(navigator) {
        LamaNavigator(navigator)
    }

    // Launch an effect to track changes to the current back stack entry, and push them
    // as a screen views to analytics
    LaunchedEffect(backstack.topRecord) {
        val topScreen = backstack.topRecord?.screen as? LamaScreen
//        analytics.trackScreenView(
//            name = topScreen?.name ?: "unknown screen",
//            arguments = topScreen?.arguments,
//        )
    }

    ProvideStrings {
        CompositionLocalProvider(
//            LocalImageLoader provides imageLoader,
            LocalLamaDateFormatter provides lamaDateFormatter,
            LocalWindowSizeClass provides calculateWindowSizeClass()
        ) {
            CircuitCompositionLocals(circuitConfig) {
                LamaTheme(
                    useDarkColors = preferences.shouldUseDarkColors(),
                    useDynamicColors = preferences.shouldUseDynamicColors()
                ) {
                    Home(
                        backstack = backstack,
                        navigator = tiviNavigator,
                        modifier = modifier,
                    )
                }
            }
        }
    }
}

private class LamaNavigator(private val navigator: Navigator) : Navigator {
    override fun goTo(screen: Screen) = when(screen) {
        is LamaScreen -> navigator.goTo(screen)
        else -> throw IllegalArgumentException("Unknown screen $screen")
    }

    override fun pop(): Screen? = navigator.pop()

    override fun resetRoot(newRoot: Screen): List<Screen> = navigator.resetRoot(newRoot)
}

@Composable
fun LamaPreferences.shouldUseDarkColors(): Boolean {
    val themePreference = remember { observeTheme() }.collectAsState(initial = theme)
    return when (themePreference.value) {
        LamaPreferences.Theme.LIGHT -> false
        LamaPreferences.Theme.DARK -> true
        else -> isSystemInDarkTheme()
    }
}

@Composable
fun LamaPreferences.shouldUseDynamicColors(): Boolean {
    return remember { observeUseDynamicColors() }
        .collectAsState(initial = useDynamicColors)
        .value
}

