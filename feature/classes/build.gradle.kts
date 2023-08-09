plugins {
    id("app.lama.android.library")
    id("app.lama.kotlin.multiplatform")
    id("app.lama.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:base"))
                implementation(project(":common:ui"))
                implementation(project(":domain"))
                implementation(project(":api:zen"))

                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.animation)
                implementation(libs.molecule)
                implementation(libs.circuit.overlay)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.activity.compose)
            }
        }
    }
}

android {
    namespace = "me.mauricee.lama.classes"
}