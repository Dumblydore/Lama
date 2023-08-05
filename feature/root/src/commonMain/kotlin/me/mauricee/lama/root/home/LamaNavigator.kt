package me.mauricee.lama.root.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import kotlinx.coroutines.CoroutineScope
import me.mauricee.lama.ui.theme.LamaTheme
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

typealias LamaContent = @Composable (
    backstack: SaveableBackStack,
    navigator: Navigator,
    onOpenUrl: (String) -> Unit,
    modifier: Modifier,
) -> Unit

@Inject
@Composable
fun LamaContent(
    @Assisted backstack: SaveableBackStack,
    @Assisted navigator: Navigator,
    @Assisted onOpenUrl: (String) -> Unit,
    rootViewModel: (CoroutineScope) -> RootViewModel,
    circuitConfig: CircuitConfig,
//    analytics: Analytics,
//    tiviDateFormatter: LamaDateFormatter,
//    tiviTextCreator: LamaTextCreator,
//    preferences: LamaPreferences,
//    imageLoader: ImageLoader,
    @Assisted modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    remember { rootViewModel(coroutineScope) }

    val lamaNavigator: Navigator = remember(navigator) {
        LamaNavigator(navigator, onOpenUrl)
    }

    // Launch an effect to track changes to the current back stack entry, and push them
    // as a screen views to analytics
//    LaunchedEffect(backstack.topRecord) {
//        val topScreen = backstack.topRecord?.screen as? LamaScreen
//        analytics.trackScreenView(
//            name = topScreen?.name ?: "unknown screen",
//            arguments = topScreen?.arguments,
//        )
//    }

//    ProvideStrings {
//        CompositionLocalProvider(
//            LocalNavigator provides lamaNavigator,
//            LocalImageLoader provides imageLoader,
//            LocalLamaDateFormatter provides tiviDateFormatter,
//            LocalLamaTextCreator provides tiviTextCreator,
//            LocalWindowSizeClass provides calculateWindowSizeClass(),
//        ) {
    CircuitCompositionLocals(circuitConfig) {
//        LamaTheme(
//            useDarkColors = false,
//            useDynamicColors = false,
//        ) {
            Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary))
//            Home(
//                backstack = backstack,
//                navigator = lamaNavigator,
//                modifier = modifier,
//            )
//        }
    }
}
//    }
//}

private class LamaNavigator(
    private val navigator: Navigator,
    private val onOpenUrl: (String) -> Unit,
) : Navigator {
    override fun goTo(screen: Screen) {
        when (screen) {
//            is UrlScreen -> onOpenUrl(screen.url)
            else -> navigator.goTo(screen)
        }
    }

    override fun pop(): Screen? = navigator.pop()

    override fun resetRoot(newRoot: Screen): List<Screen> = navigator.resetRoot(newRoot)
}
