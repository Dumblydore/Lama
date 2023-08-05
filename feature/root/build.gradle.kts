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
                implementation(project(":feature:login"))
                api(libs.kotlininject.runtime)

                implementation(libs.circuit.foundation)
                implementation(libs.circuit.overlay)

                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.animation)
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
    namespace = "me.mauricee.lama.root"
}