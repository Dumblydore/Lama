package me.mauricee.lama.ui.components.lazy

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.mauricee.lama.ui.LocalStrings
import me.mauricee.lama.ui.UIState

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun LazyLayout(
    state: UIState,
    modifier: Modifier = Modifier,
    noContent: @Composable () -> Unit = { },
    loadingContent: @Composable () -> Unit = { DefaultLoadingState() },
    errorContent: @Composable (UIState.Error) -> Unit = { error ->
        ErrorState(
            errorMessage = error.message(),
            modifier = Modifier.fillMaxSize()
        )
    },
    content: @Composable () -> Unit
) = AnimatedContent(
    transitionSpec = {
        fadeIn(animationSpec = tween(220, delayMillis = 90)) with fadeOut(animationSpec = tween(90))
    },
    targetState = state,
    modifier = modifier
) { targetState ->
    when (targetState) {
        UIState.None -> noContent()
        UIState.Loading -> loadingContent()
        is UIState.Error -> errorContent(targetState)
        UIState.Success -> content()
    }
}

@Composable
fun DefaultLoadingState() = Box(Modifier.fillMaxSize()) {
    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.Error,
    errorTint: Color = Color.Unspecified,
    errorMessage: String? = LocalStrings.current.defaultError,
) = Box(modifier = modifier) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .align(Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(4.dp)
                    )
                    .padding(vertical = 16.dp, horizontal = 24.dp)
            ) {
                Icon(
                    imageVector = imageVector,
                    tint = errorTint,
                    contentDescription = errorMessage,
                    modifier = Modifier.size(24.dp)
                )
            }
            errorMessage?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}