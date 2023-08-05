import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("app.lama.kotlin.multiplatform")
    id("app.lama.android.library")
    id("app.lama.compose")
    alias(libs.plugins.ksp)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":common:ui"))
                api(project(":feature:root"))
                api(project(":feature:login"))

                api(project(":api:zen"))

                api(libs.kotlininject.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        targets.withType<KotlinNativeTarget>().configureEach {
            binaries.withType<Framework> {
                isStatic = true
                baseName = "TiviKt"

                export(project(":feature:root"))
//                export(projects.core.analytics)
//                export(projects.data.traktauth)
            }
        }
    }
}

android {
    namespace = "me.mauricee.lama"
}

ksp {
    arg("me.tatarka.inject.generateCompanionExtensions", "true")
}

//addKspDependencyForAllTargets(libs.kotlininject.compiler)