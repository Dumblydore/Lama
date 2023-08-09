plugins {
    id("app.lama.android.library")
    id("app.lama.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:base"))
                api(libs.multiplatformsettings.core)
                api(libs.multiplatformsettings.coroutines)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core)
                implementation(libs.kotlininject.runtime)
            }
        }
    }
}

android {
    namespace = "app.lama.core.preferences"
}
