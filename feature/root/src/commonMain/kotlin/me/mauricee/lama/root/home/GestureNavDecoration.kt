package me.mauricee.lama.root.home

import com.slack.circuit.runtime.Navigator

internal expect class GestureNavDecoration(
    navigator: Navigator,
) : NavDecorationWithPrevious
