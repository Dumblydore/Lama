package me.mauricee.lama.classes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.navigationBars
import com.moriatsushi.insetsx.systemBars
import com.slack.circuit.overlay.LocalOverlayHost
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import kotlinx.coroutines.launch
import me.mauricee.lama.classes.details.ClassDetailOverlay
import me.mauricee.lama.classes.details.classDetailsPresenter
import me.mauricee.lama.ui.LocalLamaDateFormatter
import me.mauricee.lama.ui.LocalStrings
import me.mauricee.lama.ui.base.ClassesScreen
import me.mauricee.lama.ui.components.LamaTabRow
import me.tatarka.inject.annotations.Inject


sealed interface ClassesEvent : CircuitUiEvent {
    data class Register(val item: ClassListItem) : ClassesEvent
    data class SelectDay(val day: ClassDay) : ClassesEvent
}


@Inject
class ClassesUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is ClassesScreen -> {
            ui<ClassesState> { state, modifier ->
                Classes(state, modifier)
            }
        }

        else -> null
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun Classes(
    state: ClassesState,
    modifier: Modifier = Modifier,
) {
    val eventSink = state.eventSink
    val strings = LocalStrings.current.home.classes
    val formatter = LocalLamaDateFormatter.current
    val selectedDay by remember { derivedStateOf { state.days[state.selectedDayIndex] } }
    var selectedTab by remember { mutableStateOf(0) }
    val overlay = LocalOverlayHost.current

    val scope = rememberCoroutineScope()
    val lazyScrollState = rememberLazyListState(
        (state.selectedDayIndex - 3).coerceAtLeast(3),
        -(with(LocalDensity.current) { 24.dp.roundToPx() })
    )
    val pagerScrollState = rememberPagerState(state.selectedDayIndex) { state.days.size }

    val tabs by remember(strings) {
        derivedStateOf { listOf(strings.membershipTab, strings.reservedTab, strings.dropInTab) }
    }

    val register: (ClassListItem) -> Unit = remember { { eventSink(ClassesEvent.Register(it)) } }
    val date: (ClassDay) -> Unit = remember { { eventSink(ClassesEvent.SelectDay(it)) } }

    LaunchedEffect(state.selectedDayIndex) {
        if (!pagerScrollState.isScrollInProgress) {
            pagerScrollState.animateScrollToPage(state.selectedDayIndex)
        }
    }

    LaunchedEffect(pagerScrollState.isScrollInProgress, pagerScrollState.targetPage) {
        if (!pagerScrollState.isScrollInProgress && pagerScrollState.targetPage != state.selectedDayIndex) {
            date(state.days[pagerScrollState.targetPage])
        }
    }


    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top))
                    .clipToBounds()
                    .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TopAppBar(
                        title = { Text("Forged Barbell") },
                    )
                    LamaTabRow(
                        selectedTabIndex = selectedTab,
                        tabs = tabs,
                        onTabSelected = { selectedTab = it },
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = formatter.formatMonthAndYear(selectedDay.date),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    LazyRow(
                        state = lazyScrollState,
                        contentPadding = PaddingValues(horizontal = 8.dp),
                    ) {
                        items(
                            items = state.days,
                            key = { it.id }
                        ) {
                            DayRowItem(
                                day = it,
                                date = date,
                                isSelected = state.selectedDayIndex == it.id,
                                modifier = Modifier.size(48.dp, 52.dp)
                            )
                        }
                    }

                    Divider(Modifier.fillMaxWidth())
                }
            }
        },
        contentWindowInsets = WindowInsets.systemBars.exclude(WindowInsets.navigationBars),
        modifier = modifier,
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues).fillMaxWidth()
        ) {
            HorizontalPager(
                state = pagerScrollState,
                key = { state.days[it].id },
                modifier = Modifier.fillMaxSize()
            ) { index ->
                val day = remember(index, state.days) { state.days[index] }
                val pageState by remember(day) {
                    classPagePresenter(
                        scope,
                        day.date,
                        state.getClasses
                    )
                }.collectAsState()
                ClassDayPage(
                    state = pageState,
                    register = register,
                    classItem = {
                        scope.launch {
                            overlay.show(
                                ClassDetailOverlay(
                                    stateFlow = classDetailsPresenter(
                                        scope = scope,
                                        classId = it.id,
                                        getClassDetails = state.getClassDetail
                                    )
                                )
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }
}


@Composable
fun DayRowItem(
    day: ClassDay,
    isSelected: Boolean,
    date: (ClassDay) -> Unit,
    modifier: Modifier
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.clickable { date(day) }.then(modifier).height(IntrinsicSize.Min)
) {
    val formatter = LocalLamaDateFormatter.current
    val textColor =
        if (day.isToday) MaterialTheme.colorScheme.onPrimaryContainer else Color.Unspecified
    if (day.isToday) {
        Box(
            Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(horizontal = 4.dp)
                .aspectRatio(1f)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                )
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            style = MaterialTheme.typography.labelMedium,
            text = formatter.formatDayOfWeek(day.date.dayOfWeek).first().toString(),
            color = textColor
        )
        Text(
            style = MaterialTheme.typography.labelLarge,
            text = day.date.dayOfMonth.toString(),
            color = textColor
        )
    }

    AnimatedVisibility(
        visible = isSelected,
        enter = expandIn(expandFrom = Alignment.BottomCenter),
        exit = shrinkOut(shrinkTowards = Alignment.BottomCenter),
        modifier = Modifier.align(Alignment.BottomCenter)
    ) {
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .height(2.dp)
        )
    }
}



