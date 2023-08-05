


plugins {
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

//spotless {
//    kotlin {
//        target("src/**/*.kt")
//        ktlint(libs.versions.ktlint.get())
//    }
//
//    kotlinGradle {
//        target("*.kts")
//        ktlint(libs.versions.ktlint.get())
//    }
//}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "app.lama.kotlin.multiplatform"
            implementationClass = "app.lama.gradle.KotlinMultiplatformConventionPlugin"
        }

        register("root") {
            id = "app.lama.root"
            implementationClass = "app.lama.gradle.RootConventionPlugin"
        }

        register("kotlinAndroid") {
            id = "app.lama.kotlin.android"
            implementationClass = "app.lama.gradle.KotlinAndroidConventionPlugin"
        }

        register("androidApplication") {
            id = "app.lama.android.application"
            implementationClass = "app.lama.gradle.AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "app.lama.android.library"
            implementationClass = "app.lama.gradle.AndroidLibraryConventionPlugin"
        }

        register("androidTest") {
            id = "app.lama.android.test"
            implementationClass = "app.lama.gradle.AndroidTestConventionPlugin"
        }

        register("compose") {
            id = "app.lama.compose"
            implementationClass = "app.lama.gradle.ComposeMultiplatformConventionPlugin"
        }
    }
}
