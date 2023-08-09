package me.mauricee.lama.root.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import me.mauricee.lama.common.resource.strings.LamaStrings
import me.mauricee.lama.ui.LocalStrings
import me.mauricee.lama.ui.LocalWindowSizeClass
import me.mauricee.lama.ui.base.ClassesScreen
import me.mauricee.lama.ui.base.LoginScreen

@Composable
internal fun Home(
    backstack: SaveableBackStack,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    val windowSizeClass = LocalWindowSizeClass.current
    val navigationType = remember(windowSizeClass) {
        NavigationType.forWindowSizeSize(windowSizeClass)
    }

    val rootScreen by remember {
        derivedStateOf { backstack.last().screen }
    }

    val displayNavigation by remember(backstack.topRecord) { derivedStateOf { backstack.topRecord?.screen !is LoginScreen } }

    val strings = LocalStrings.current
    val navigationItems = remember(strings) { buildNavigationItems(strings) }
    ContentWithOverlays {
        Scaffold(
            bottomBar = {
                if (displayNavigation && navigationType == NavigationType.BOTTOM_NAVIGATION) {
                    HomeNavigationBar(
                        selectedNavigation = rootScreen,
                        navigationItems = navigationItems,
                        onNavigationSelected = { navigator.resetRoot(it) },
                        modifier = Modifier.fillMaxWidth(),
                    )
                } else {
                    Spacer(
                        Modifier
                            .windowInsetsBottomHeight(WindowInsets.navigationBars)
                            .fillMaxWidth(),
                    )
                }
            },
            contentWindowInsets = WindowInsets.systemBars.exclude(WindowInsets.statusBars),
            modifier = modifier,
        ) { paddingValues ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                if (displayNavigation) {
                    if (navigationType == NavigationType.RAIL) {
                        HomeNavigationRail(
                            selectedNavigation = rootScreen,
                            navigationItems = navigationItems,
                            onNavigationSelected = { navigator.resetRoot(it) },
                            modifier = Modifier.fillMaxHeight(),
                        )

                        Divider(
                            Modifier
                                .fillMaxHeight()
                                .width(1.dp),
                        )
                    } else if (navigationType == NavigationType.PERMANENT_DRAWER) {
                        HomeNavigationDrawer(
                            selectedNavigation = rootScreen,
                            navigationItems = navigationItems,
                            onNavigationSelected = { navigator.resetRoot(it) },
                            modifier = Modifier.fillMaxHeight(),
                        )
                    }
                }


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


private fun buildNavigationItems(strings: LamaStrings): List<HomeNavigationItem> {
    return listOf(
        HomeNavigationItem(
            screen = ClassesScreen,
            label = strings.home.navigationItems.classes,
            contentDescription = strings.home.navigationItems.classes,
            iconImageVector = Icons.Outlined.CalendarMonth,
            selectedImageVector = Icons.Default.CalendarMonth,
        ),
    )
}