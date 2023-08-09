// Copyright 2023, Christopher Banes and the Tivi project contributors
// SPDX-License-Identifier: Apache-2.0

package me.mauricee.lama.di

import me.mauricee.lama.core.base.di.ActivityScope
import me.mauricee.lama.root.home.LamaUiViewController
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import platform.UIKit.UIViewController

@ActivityScope
@Component
abstract class HomeUiControllerComponent(
    @Component val applicationComponent: IosApplicationComponent,
) : UiComponent {
    abstract val uiViewControllerFactory: () -> UIViewController

    @Provides
    @ActivityScope
    fun uiViewController(bind: LamaUiViewController): UIViewController = bind()

    companion object
}
