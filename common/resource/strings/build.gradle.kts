plugins {
    id("app.lama.android.library")
    id("app.lama.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:base"))
                api(libs.lyricist.core)
                api(libs.kotlinx.datetime)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.assertk)
            }
        }
    }
}

android {
    namespace = "me.mauricee.lama.common.resource.strings"
}
