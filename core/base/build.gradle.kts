plugins {
    id("app.lama.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlin.coroutines.core)
                api(libs.kotlinx.datetime)
                api(libs.kotlininject.runtime)
            }
        }

        val jvmMain by getting
    }
}
