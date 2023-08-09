plugins {
    id("app.lama.android.application")
    id("app.lama.kotlin.android")
    alias(libs.plugins.ksp)
    id("app.lama.compose")
}

android {
    namespace = "me.mauricee.lama.android"
    defaultConfig {
        applicationId = "me.mauricee.lama.android"
        versionCode = 1
        versionName = "0.1"
    }
    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(libs.androidx.activity.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.emoji)
    implementation(libs.androidx.preference)
    implementation(libs.androidx.profileinstaller)

    implementation(libs.kotlin.coroutines.android)

    implementation(libs.google.firebase.crashlytics)

    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.multiplatformsettings.core)
    implementation(libs.multiplatformsettings.coroutines)

    ksp(libs.kotlininject.compiler)

    androidTestImplementation(libs.androidx.uiautomator)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
}