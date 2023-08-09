plugins {
    id("app.lama.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:base"))
//                api(projects.data.models)
                api(libs.paging.common)
            }
        }
    }
}
