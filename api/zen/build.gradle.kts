plugins {
    id("app.lama.kotlin.multiplatform")
    id("kotlinx-serialization")
    alias(libs.plugins.buildConfig)
}

buildConfig {
    packageName("app.lama.tmdb")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.auth)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.serialization.kotlinx.json)
                implementation(libs.ktor.client.content.negotiation)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                api(libs.kotlin.coroutines.core)

                api(libs.kotlininject.runtime)
            }
        }
        val jvmMain by getting {
            dependencies {
                api(libs.okhttp.okhttp)
                implementation(libs.ktor.client.okhttp)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}