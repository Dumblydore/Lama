kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.base)
                api(projects.core.logging.api)
                implementation(libs.kermit.kermit)
                implementation(libs.kotlininject.runtime)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.crashkios.crashlytics)
                implementation(libs.timber)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.crashkios.crashlytics)
            }
        }
    }
}

android {
    namespace = "app.tivi.core.logging"
}
