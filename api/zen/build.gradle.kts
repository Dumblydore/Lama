plugins {
    id("app.lama.android.library")
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
                implementation(project(":core:base"))

                api(libs.ktor.client.core)
                api(libs.ktor.client.auth)
                api(libs.ktor.client.serialization)
                api(libs.ktor.client.serialization.kotlinx.json)
                api(libs.ktor.client.content.negotiation)
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                api(libs.kotlin.coroutines.core)
                api(libs.kotlinx.datetime)

                implementation(libs.kotlininject.runtime)
            }
        }

        val jvmCommon by creating {
            dependsOn(commonMain)
            dependencies {
                api(libs.okhttp.okhttp)
                implementation(libs.ktor.client.okhttp)
            }
        }

        val jvmMain by getting {
            dependsOn(jvmCommon)
        }

        val androidMain by getting {
            dependsOn(jvmCommon)
            dependencies {
                implementation(libs.androidx.activity.activity)
                implementation(libs.androidx.browser)
                implementation(libs.androidx.core)

                implementation(libs.playservices.blockstore)
                implementation(libs.kotlinx.coroutines.playservices)

                implementation(libs.kotlininject.runtime)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.multiplatformsettings.core)
            }
        }
    }
}

android {
    namespace = "me.mauricee.lama.root"
}