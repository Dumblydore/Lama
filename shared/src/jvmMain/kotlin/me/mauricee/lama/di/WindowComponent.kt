package me.mauricee.lama.di

import me.mauricee.lama.core.base.di.ActivityScope
import me.mauricee.lama.root.LamaContent
import me.tatarka.inject.annotations.Component

@ActivityScope
@Component
abstract class WindowComponent(
    @Component val applicationComponent: DesktopApplicationComponent,
) : UiComponent {
    abstract val lamaContent: LamaContent

    companion object
}
