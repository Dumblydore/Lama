package me.mauricee.lama.root.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material.icons.outlined.Weekend
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.navigationBars
import com.moriatsushi.insetsx.safeContentPadding
import com.moriatsushi.insetsx.statusBars
import com.moriatsushi.insetsx.systemBars
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.foundation.screen
import com.slack.circuit.overlay.ContentWithOverlays
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import me.mauricee.lama.ui.base.LoginScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Home(
    backstack: SaveableBackStack,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
//    val windowSizeClass = LocalWindowSizeClass.current
//    val navigationType = remember(windowSizeClass) {
//        NavigationType.forWindowSizeSize(windowSizeClass)
//    }

    val rootScreen by remember {
        derivedStateOf { backstack.last().screen }
    }

//    val strings = LocalStrings.current
//    val navigationItems = remember(strings) { buildNavigationItems(strings) }

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars.exclude(WindowInsets.statusBars),
        modifier = modifier,
    ) { paddingValues ->
        Row(
            modifier = Modifier

                .fillMaxSize()
                .padding(paddingValues),
        ) {
//            if (navigationType == NavigationType.RAIL) {
//            HomeNavigationRail(
//                selectedNavigation = rootScreen,
//                navigationItems = remember { buildNavigationItems() },
//                onNavigationSelected = { navigator.resetRoot(it) },
//                modifier = Modifier.fillMaxHeight(),
//            )
//
//                Divider(
//                    Modifier
//                        .fillMaxHeight()
//                        .width(1.dp),
//                )
//            } else if (navigationType == NavigationType.PERMANENT_DRAWER) {
//                HomeNavigationDrawer(
//                    selectedNavigation = rootScreen,
//                    navigationItems = navigationItems,
//                    onNavigationSelected = { navigator.resetRoot(it) },
//                    modifier = Modifier.fillMaxHeight(),
//                )
//            }

            ContentWithOverlays {
                NavigableCircuitContentWithPrevious(
                    navigator = navigator,
                    backstack = backstack,
                    decoration = GestureNavDecoration(navigator),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        navigator.resetRoot(LoginScreen)
    }
}

@Composable
private fun HomeNavigationBar(
    selectedNavigation: Screen,
    navigationItems: List<HomeNavigationItem>,
    onNavigationSelected: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        windowInsets = WindowInsets.navigationBars,
    ) {
        for (item in navigationItems) {
            NavigationBarItem(
                icon = {
                    HomeNavigationItemIcon(
                        item = item,
                        selected = selectedNavigation == item.screen,
                    )
                },
                label = { Text(text = item.label) },
                selected = selectedNavigation == item.screen,
                onClick = { onNavigationSelected(item.screen) },
            )
        }
    }
}

@Composable
private fun HomeNavigationRail(
    selectedNavigation: Screen,
    navigationItems: List<HomeNavigationItem>,
    onNavigationSelected: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        for (item in navigationItems) {
            NavigationRailItem(
                icon = {
                    HomeNavigationItemIcon(
                        item = item,
                        selected = selectedNavigation == item.screen,
                    )
                },
                alwaysShowLabel = false,
                label = { Text(text = item.label) },
                selected = selectedNavigation == item.screen,
                onClick = { onNavigationSelected(item.screen) },
            )
        }
    }
}

@Composable
private fun HomeNavigationDrawer(
    selectedNavigation: Screen,
    navigationItems: List<HomeNavigationItem>,
    onNavigationSelected: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .safeContentPadding()
            .padding(16.dp)
            .widthIn(max = 280.dp),
    ) {
        for (item in navigationItems) {
            @OptIn(ExperimentalMaterial3Api::class)
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = item.iconImageVector,
                        contentDescription = item.contentDescription,
                    )
                },
                label = { Text(text = item.label) },
                selected = selectedNavigation == item.screen,
                onClick = { onNavigationSelected(item.screen) },
            )
        }
    }
}

@Composable
private fun HomeNavigationItemIcon(item: HomeNavigationItem, selected: Boolean) {
    if (item.selectedImageVector != null) {
        Crossfade(targetState = selected) { s ->
            Icon(
                imageVector = if (s) item.selectedImageVector else item.iconImageVector,
                contentDescription = item.contentDescription,
            )
        }
    } else {
        Icon(
            imageVector = item.iconImageVector,
            contentDescription = item.contentDescription,
        )
    }
}

@Immutable
private data class HomeNavigationItem(
    val screen: Screen,
    val label: String,
    val contentDescription: String,
    val iconImageVector: ImageVector,
    val selectedImageVector: ImageVector? = null,
)

internal enum class NavigationType {
    BOTTOM_NAVIGATION,
    RAIL,
    PERMANENT_DRAWER,
    ;

    companion object {
        fun forWindowSizeSize(windowSizeClass: WindowSizeClass): NavigationType = when {
            windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact -> BOTTOM_NAVIGATION
            windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact -> BOTTOM_NAVIGATION
            windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium -> RAIL
            else -> PERMANENT_DRAWER
        }
    }
}


private fun buildNavigationItems(): List<HomeNavigationItem> {
    return listOf(
        HomeNavigationItem(
            screen = LoginScreen,
            label = "strings.discoverTitle",
            contentDescription = "strings.cdDiscoverTitle",
            iconImageVector = Icons.Outlined.Weekend,
            selectedImageVector = Icons.Default.Weekend,
        ),
        HomeNavigationItem(
            screen = LoginScreen,
            label = "strings.upnextTitle",
            contentDescription = "strings.cdUpnextTitle",
            iconImageVector = Icons.Default.Subscriptions,
        ),
        HomeNavigationItem(
            screen = LoginScreen,
            label = "strings.libraryTitle",
            contentDescription = "strings.cdLibraryTitle",
            iconImageVector = Icons.Outlined.VideoLibrary,
            selectedImageVector = Icons.Default.VideoLibrary,
        ),
        HomeNavigationItem(
            screen = LoginScreen,
            label = "strings.searchNavigationTitle",
            contentDescription = "strings.cdSearchNavigationTitle",
            iconImageVector = Icons.Default.Search,
        ),
    )
}