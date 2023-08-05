plugins {
    id("app.lama.kotlin.multiplatform")
    id("app.lama.android.library")
    id("app.lama.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:base"))
                implementation(project(":common:ui"))
                implementation(project(":api:zen"))

                api(libs.kotlin.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "me.mauricee.lama.classes"
}