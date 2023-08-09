package me.mauricee.lama.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy

@Composable
fun OverlappingRow(
    modifier: Modifier = Modifier,
    overlapFactor: Float = DefaultOverlapFactor,
    content: @Composable () -> Unit
) {
    val measurePolicy = overlappingRowMeasurePolicy(overlapFactor)
    Layout(
        measurePolicy = measurePolicy,
        content = content,
        modifier = modifier
    )
}

fun overlappingRowMeasurePolicy(overlapFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { measurable -> measurable.measure(constraints) }
    val height = placeables.maxOf { it.height }
    val width = (placeables.subList(1, placeables.size)
        .sumOf { it.width } * overlapFactor + placeables[0].width).toInt()
    layout(width, height) {
        var xPos = 0
        for (placeable in placeables) {
            placeable.placeRelative(xPos, 0, 0f)
            xPos += (placeable.width * overlapFactor).toInt()
        }
    }
}

const val DefaultOverlapFactor = 0.5f