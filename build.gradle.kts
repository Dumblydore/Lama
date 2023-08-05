plugins {
    //trick: for the same plugin versions in all sub-modules
    id("org.jetbrains.kotlin.plugin.serialization").version("1.8.21").apply(false)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.cacheFixPlugin) apply false
    alias(libs.plugins.android.lint) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
//    alias(libs.plugins.gms.googleServices) apply false
//    alias(libs.plugins.firebase.crashlytics) apply false
//    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    kotlin("multiplatform").version("1.8.21").apply(false)
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
