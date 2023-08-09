package me.mauricee.lama.core.base.app

data class ApplicationInfo(
    val packageName: String,
    val debugBuild: Boolean,
    val versionName: String,
    val versionCode: Int,
)
