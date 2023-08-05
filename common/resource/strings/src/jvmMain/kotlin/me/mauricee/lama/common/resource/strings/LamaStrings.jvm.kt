package me.mauricee.lama.common.resource.strings

actual fun String.fmt(vararg args: Any?): String = format(*args)