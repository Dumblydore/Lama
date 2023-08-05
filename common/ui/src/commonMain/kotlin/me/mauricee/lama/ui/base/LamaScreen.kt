package me.mauricee.lama.ui.base

import com.slack.circuit.runtime.Screen
import me.mauricee.lama.ui.CommonParcelize

@CommonParcelize
object LoginScreen : LamaScreen("login()")

abstract class LamaScreen(val name: String) : Screen {
    open val arguments: Map<String, *>? = null
}