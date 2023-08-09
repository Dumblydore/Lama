package me.mauricee.lama.android

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.core.view.WindowCompat
import me.mauricee.lama.android.inject.ActivityComponent
import me.mauricee.lama.android.inject.AndroidApplicationComponent
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.rememberCircuitNavigator
import me.mauricee.lama.core.base.di.ActivityScope
import me.mauricee.lama.di.UiComponent
import me.mauricee.lama.root.LamaContent
import me.mauricee.lama.ui.base.LoginScreen
import me.mauricee.lama.ui.base.StartScreen
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = MainActivityComponent::class.create(this)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val backstack = rememberSaveableBackStack { push(StartScreen) }
            val navigator = rememberCircuitNavigator(backstack)

            component.lamaContent(
                backstack,
                navigator,
                modifier = Modifier.semantics {
                    // Enables testTag -> UiAutomator resource id
                    // See https://developer.android.com/jetpack/compose/testing#uiautomator-interop
                    @OptIn(ExperimentalComposeUiApi::class)
                    testTagsAsResourceId = BuildConfig.DEBUG
                },
            )
        }
    }
}


@Component
@ActivityScope
abstract class MainActivityComponent(
    @get:Provides override val activity: Activity,
    @Component val applicationComponent: AndroidApplicationComponent = AndroidApplicationComponent.from(activity),
) : UiComponent, ActivityComponent {
    abstract val lamaContent: LamaContent
}

