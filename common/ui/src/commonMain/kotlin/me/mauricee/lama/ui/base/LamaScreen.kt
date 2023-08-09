package me.mauricee.lama.ui.base

import com.slack.circuit.runtime.Screen
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import me.mauricee.lama.core.base.util.now
import me.mauricee.lama.ui.CommonParcelize

@CommonParcelize
object StartScreen : LamaScreen("start()")

@CommonParcelize
object LoginScreen : LamaScreen("login()")

@CommonParcelize
object ClassesScreen : LamaScreen("classes()")

abstract class LamaScreen(val name: String) : Screen {
    open val arguments: Map<String, *>? = null
}