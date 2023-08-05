package me.mauricee.lama.root.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.Navigator

internal actual class GestureNavDecoration actual constructor(
    navigator: Navigator,
) : NavDecorationWithPrevious {
    @Composable
    override fun <T> DecoratedContent(
        arg: T,
        previous: T?,
        backStackDepth: Int,
        modifier: Modifier,
        content: @Composable (T) -> Unit,
    ) {
        // On Desktop we just use the built-in DefaultDecoration
        DefaultDecoration.DecoratedContent(
            arg = arg,
            backStackDepth = backStackDepth,
            modifier = modifier,
            content = content,
        )
    }
}

