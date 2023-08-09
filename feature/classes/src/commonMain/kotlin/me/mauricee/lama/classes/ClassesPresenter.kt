package me.mauricee.lama.classes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import me.mauricee.lama.classes.details.ClassDetails
import me.mauricee.lama.core.base.util.endOfYear
import me.mauricee.lama.core.base.util.now
import me.mauricee.lama.core.base.util.rangeTo
import me.mauricee.lama.core.base.util.startOfYear
import me.mauricee.lama.ui.UIState
import me.mauricee.lama.ui.asUiState
import me.mauricee.lama.ui.base.ClassesScreen
import me.mauricee.lama.ui.base.asyncProperty
import me.mauricee.lama.ui.uiState
import me.mauricee.lama.users.ObserveUser
import me.mauricee.lama.users.User
import me.mauricee.lama.users.UserId
import me.mauricee.lama.zen.management.calendar.ClassesApi
import me.tatarka.inject.annotations.Inject

@Inject
class ClassesUiPresenterFactory(
    private val presenterFactory: (Navigator) -> ClassesPresenter,
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is ClassesScreen -> presenterFactory(navigator)
            else -> null
        }
    }
}
//URL	https://zenplanner-library.s3.amazonaws.com/{partitionId}/Students/{img}
@Inject
class ClassesPresenter(
    private val observeUser: ObserveUser,
    private val classesApi: ClassesApi
) : Presenter<ClassesState> {
    @Composable
    override fun present(): ClassesState {
        val now = remember { LocalDate.now() }
        val days = remember {
            (LocalDate.startOfYear()..LocalDate.endOfYear())
                .asSequence()
                .mapIndexed { index, date -> ClassDay(index, date, date == now) }
                .toList()
        }

        var uiState by uiState()
        var user by remember { mutableStateOf<User?>(null) }

        LaunchedEffect(Unit) {
            observeUser.flow
                .onStart { uiState = UIState.Loading }
                .onStart { observeUser(UserId.Me) }
                .collect {
                    user = it
                    uiState = UIState.Success
                }
        }


        var selectedDayIndex by remember {
            mutableStateOf(
                LocalDate.startOfYear().daysUntil(now)
            )
        }

        return ClassesState(
            state = uiState,
            user = user,
            days = days,
            selectedDayIndex = selectedDayIndex,
            getClassDetail = { id ->
                runCatching {
                    val response = classesApi.getClass(
                        snapshotFlow { user }.mapNotNull { it?.id }.first(),
                        id
                    ).classDetails
                    ClassDetails(
                        name = (response.item.displayName ?: response.item.programName ?: response.item.name).orEmpty(),
                        description = response.item.description.orEmpty(),
                        instructorName = response.primaryInstructor.primaryInstructorDisplayName,
                        instructorImage = response.primaryInstructor.primaryInstructorPhoto.orEmpty(),
                        date = response.schedule.start.toLocalDateTime(TimeZone.currentSystemDefault()).date,
                        start = response.schedule.start.toLocalDateTime(TimeZone.currentSystemDefault()).time,
                        end = response.schedule.end.toLocalDateTime(TimeZone.currentSystemDefault()).time,
                    )
                }
            },
            getClasses = { date ->
                runCatching {
                    classesApi.getClassesForDate(
                        snapshotFlow { user }.mapNotNull { it?.id }.first(),
                        date
                    ).map {
                        ClassListItem(
                            id = it.item.itemId,
                            name = it.item.name,
                            startTime = it.schedule.start.toLocalDateTime(TimeZone.currentSystemDefault()).time,
                            endTime = it.schedule.end.toLocalDateTime(TimeZone.currentSystemDefault()).time,
                            capacity = Capacity(
                                totalSpots = it.capacity.totalSpots,
                                takenSpots = it.capacity.totalSpots - it.capacity.remainingSpots,
                                waitlistTotalSpots = it.capacity.waitlistTotalSpots,
                                waitlistRemainingSpots = it.capacity.waitlistRemainingSpots,
                                isFull = it.capacity.isFull
                            ),
                            instructorName = if (it.primaryInstructor.isPrimaryInstructorDisplayed)
                                it.primaryInstructor.primaryInstructorDisplayName else null,
                            instructorImage = null,
                            state = when {
                                it.reservation.isCheckedIn -> RegistrationState.CheckedIn
                                it.reservation.isWaitlisted -> RegistrationState.WaitListed
                                it.reservation.isReserved -> RegistrationState.Registered
                                else -> RegistrationState.NotRegistered
                            },
                        )
                    }
                }
            },
            eventSink = { event ->
                when (event) {
                    is ClassesEvent.Register -> TODO()
                    is ClassesEvent.SelectDay -> selectedDayIndex = event.day.id
                }
            }
        )
    }
}


fun classPagePresenter(
    scope: CoroutineScope,
    date: LocalDate,
    getClasses: suspend (LocalDate) -> Result<List<ClassListItem>>,
) = scope.launchMolecule(mode = RecompositionMode.ContextClock) {
    var uiState by uiState()
    val classes: List<ClassListItem> = asyncProperty(emptyList()) {
        uiState = UIState.Loading
        val result = getClasses(date)
        uiState = result.asUiState()
        result.getOrNull().orEmpty()
    }

    ClassesPageState(
        state = uiState,
        classes = classes
    )
}
