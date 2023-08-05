package me.mauricee.lama.root

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.screen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import me.mauricee.lama.root.home.Home
import me.mauricee.lama.ui.LocalWindowSizeClass
import me.mauricee.lama.ui.ProvideStrings
import me.mauricee.lama.ui.base.LamaScreen
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
//    rootViewModel: (CoroutineScope) -> RootViewModel,
    circuitConfig: CircuitConfig,
//    analytics: Analytics,
//    tiviDateFormatter: LamaDateFormatter,
//    tiviTextCreator: LamaTextCreator,
//    preferences: LamaPreferences,
//    imageLoader: ImageLoader,
    @Assisted modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
//    remember { rootViewModel(coroutineScope) }

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
//            LocalNavigator provides tiviNavigator,
//            LocalImageLoader provides imageLoader,
//            LocalLamaDateFormatter provides tiviDateFormatter,
//            LocalLamaTextCreator provides tiviTextCreator,
            LocalWindowSizeClass provides calculateWindowSizeClass()
        ) {
            CircuitCompositionLocals(circuitConfig) {
                LamaTheme(
                    useDarkColors = true,
                    useDynamicColors = true,
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
