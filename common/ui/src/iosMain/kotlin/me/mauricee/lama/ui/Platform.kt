package me.mauricee.lama.ui

actual interface CommonParceler<T>

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
actual annotation class CommonParcelize actual constructor()
