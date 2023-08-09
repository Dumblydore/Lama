package me.mauricee.lama.classes

import androidx.compose.runtime.Immutable
import com.slack.circuit.runtime.CircuitUiState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import me.mauricee.lama.classes.details.ClassDetails
import me.mauricee.lama.ui.UIState
import me.mauricee.lama.users.User

@Immutable
data class ClassesState(
    val state: UIState,
    val user: User?,
    val days: List<ClassDay>,
    val selectedDayIndex: Int,
    val getClasses: suspend (LocalDate) -> Result<List<ClassListItem>>,
    val getClassDetail: suspend (String) -> Result<ClassDetails>,
    val eventSink: (ClassesEvent) -> Unit,
) : CircuitUiState

@Immutable
data class ClassesPageState(
    val state: UIState,
    val classes: List<ClassListItem>,
)

@Immutable
data class ClassListItem(
    val id: String,
    val name: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val capacity: Capacity,
    val instructorName: String?,
    val instructorImage: String?,
    val state: RegistrationState
)

@Immutable
data class ClassDay(
    val id: Int,
    val date: LocalDate,
    val isToday: Boolean
)

@Immutable
data class Capacity(
    val totalSpots: Int,
    val takenSpots: Int,
    val waitlistTotalSpots: Int,
    val waitlistRemainingSpots: Int,
    val isFull: Boolean,
)

enum class RegistrationState {
    NotRegistered,
    Registered,
    CheckedIn,
    WaitListed,
    Full
}