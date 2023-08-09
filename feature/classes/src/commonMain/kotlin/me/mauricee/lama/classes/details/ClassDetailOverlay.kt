package me.mauricee.lama.classes.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import com.moriatsushi.insetsx.navigationBars
import com.moriatsushi.insetsx.navigationBarsPadding
import com.moriatsushi.insetsx.systemBars
import com.slack.circuit.overlay.Overlay
import com.slack.circuit.overlay.OverlayNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import me.mauricee.lama.ui.LocalLamaDateFormatter
import me.mauricee.lama.ui.LocalStrings
import me.mauricee.lama.ui.UIState
import me.mauricee.lama.ui.asUiState
import me.mauricee.lama.ui.base.asyncProperty
import me.mauricee.lama.ui.components.lazy.LazyLayout
import me.mauricee.lama.ui.uiState
import me.tatarka.inject.annotations.Inject


@Immutable
data class ClassDetailsState(
    val state: UIState = UIState.None,
    val detail: ClassDetails? = null
)

@Immutable
data class ClassDetails(
    val name: String,
    val description: String,
    val instructorName: String?,
    val instructorImage: String,
    val date: LocalDate,
    val start: LocalTime,
    val end: LocalTime
)

@Inject
fun classDetailsPresenter(
    scope: CoroutineScope,
    classId: String,
    getClassDetails: suspend (String) -> Result<ClassDetails>,
) = scope.launchMolecule(mode = RecompositionMode.ContextClock) {
    var uiState by uiState()
    val details: ClassDetails? = asyncProperty(null) {
        uiState = UIState.Loading
        val result = getClassDetails(classId)
        uiState = result.asUiState()
        result.getOrNull()
    }

    ClassDetailsState(
        state = uiState,
        detail = details
    )
}


class ClassDetailOverlay(
    private val stateFlow: StateFlow<ClassDetailsState>
) : Overlay<Unit> {
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content(navigator: OverlayNavigator<Unit>) {
        val scope = rememberCoroutineScope()
        val modalSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
            skipHalfExpanded = false
        )

        val state by stateFlow.collectAsState()

        ModalBottomSheetLayout(
            modifier = Modifier.navigationBarsPadding().fillMaxSize(),
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            sheetContent = {
                ClassDetailsContent(
                    state = state,
                    close = { scope.launch { modalSheetState.hide() } }
                )
            }
        ) {
        }



        LaunchedEffect(modalSheetState.targetValue) {
            if (modalSheetState.targetValue == ModalBottomSheetValue.Hidden) {
                modalSheetState.hide()
                navigator.finish(Unit)
            }
        }
        LaunchedEffect(Unit) {
            modalSheetState.show()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ClassDetailsContent(
    state: ClassDetailsState,
    close: () -> Unit
) =
    Column {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = close) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null
                    )
                }
            },
            title = { Text("Class") },
            modifier = Modifier
                .clipToBounds()
                .fillMaxWidth()
        )

        LazyLayout(state.state) {
            state.detail?.let { detail ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier)
                    DateHeader(
                        date = detail.date,
                        start = detail.start,
                        end = detail.end,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )

                    Text(
                        text = detail.name,
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column {
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = detail.description,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Reserve"
                        )
                    }

                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }

@Composable
private fun DateHeader(
    date: LocalDate,
    start: LocalTime,
    end: LocalTime,
    modifier: Modifier = Modifier
) {
    val formatter = LocalLamaDateFormatter.current
    val strings = LocalStrings.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = date.month.name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "${date.dayOfMonth}",
                style = MaterialTheme.typography.labelLarge,
            )
        }

        Text(
            text = strings.range(
                formatter.formatShortTime(start),
                formatter.formatShortTime(end)
            ),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium,
        )
    }
}

// @OptIn(ExperimentalMaterial3Api::class)
// class BottomSheetOverlay<Model : Any, Result : Any>(
//     private val model: Model,
//     private val onDismiss: () -> Result,
//     private val tonalElevation: Dp = BottomSheetDefaults.Elevation,
//     private val scrimColor: Color = Color.Unspecified,
//     private val content: @Composable (Model, OverlayNavigator<Result>) -> Unit,
// ) : Overlay<Result> {
//    @OptIn(ExperimentalMaterialApi::class)
//    @Composable
//    override fun Content(navigator: OverlayNavigator<Result>) {
//        val modalSheetState = rememberModalBottomSheetState(
//            initialValue = ModalBottomSheetValue.Hidden,
//            confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
//            skipHalfExpanded = true
//        )
//
//        val coroutineScope = rememberCoroutineScope()
////        BackHandler(enabled = sheetState.isVisible) {
////            coroutineScope
////                .launch { sheetState.hide() }
////                .invokeOnCompletion {
////                    if (!sheetState.isVisible) {
////                        navigator.finish(onDismiss())
////                    }
////                }
////        }
//
//        ModalBottomSheet(
//            modifier = Modifier.fillMaxWidth(),
//            content = {
//                // Delay setting the result until we've finished dismissing
//                content(model) { result ->
//                    // This is the OverlayNavigator.finish() callback
//                    coroutineScope.launch {
//                        try {
//                            sheetState.hide()
//                        } finally {
//                            navigator.finish(result)
//                        }
//                    }
//                }
//            },
//            tonalElevation = tonalElevation,
//            scrimColor = if (scrimColor.isSpecified) scrimColor else BottomSheetDefaults.ScrimColor,
//            sheetState = sheetState,
//            onDismissRequest = { navigator.finish(onDismiss()) },
//        )
//
//        LaunchedEffect(Unit) { sheetState.show() }
//    }
// }