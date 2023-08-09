plugins {
    id("app.lama.android.library")
    id("app.lama.kotlin.multiplatform")
    id("app.lama.compose")
    alias(libs.plugins.kotlin.parcelize)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.circuit.foundation)
                api(libs.circuit.overlay)

                api(project(":core:base"))
                api(project(":common:resource:strings"))
                api(libs.lyricist.library)

                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                api(compose.material3)
                api(libs.compose.material3.windowsizeclass)
                implementation(compose.animation)

                api(libs.insetsx)
                implementation(libs.uuid)
                api(libs.kotlinx.datetime)
                implementation(libs.paging.compose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmCommon by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependsOn(jvmCommon)
        }

        val androidMain by getting {
            dependsOn(jvmCommon)
        }
    }
}

android {
    namespace = "me.mauricee.lama.ui"
}