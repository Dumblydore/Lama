plugins {
    id("app.lama.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:base"))
                api(project(":api:zen"))

                api(libs.paging.common)
                implementation(libs.kotlinx.atomicfu)
                implementation(libs.kotlininject.runtime)
            }
        }
    }
}
