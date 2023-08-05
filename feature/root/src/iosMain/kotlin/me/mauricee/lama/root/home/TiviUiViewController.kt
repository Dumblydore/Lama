package me.mauricee.lama.root.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.rememberCircuitNavigator
import me.mauricee.lama.ui.base.LoginScreen
import me.tatarka.inject.annotations.Inject
import platform.Foundation.NSURL
import platform.SafariServices.SFSafariViewController
import platform.UIKit.UIViewController

typealias LamaUiViewController = () -> UIViewController

@Inject
fun LamaUiViewController(
    lamaContent: LamaContent,
): UIViewController = ComposeUIViewController {
    val backstack = rememberSaveableBackStack { push(LoginScreen) }
    val navigator = rememberCircuitNavigator(backstack, onRootPop = { /* no-op */ })
    val uiViewController = LocalUIViewController.current

    lamaContent(
        backstack,
        navigator,
        { url ->
            val safari = SFSafariViewController(NSURL(string = url))
            uiViewController.presentViewController(safari, animated = true, completion = null)
        },
        Modifier,
    )
}
