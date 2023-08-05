plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "domain"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                val commonMain by getting {
                    dependencies {
//                        implementation(projects.core.base)

//                        api(projects.data.models)
//                        implementation(projects.data.db) // remove this eventually
//                        api(projects.data.legacy) // remove this eventually

//                        api(projects.data.episodes)
//                        api(projects.data.followedshows)
//                        api(projects.data.popularshows)
//                        api(projects.data.recommendedshows)
//                        api(projects.data.relatedshows)
//                        api(projects.data.search)
//                        api(projects.data.showimages)
//                        api(projects.data.shows)
//                        api(projects.data.traktauth)
//                        api(projects.data.traktusers)
//                        api(projects.data.trendingshows)
//                        api(projects.data.watchedshows)

//                        implementation(projects.api.tmdb)

                        api(libs.paging.common)

                        implementation(libs.kotlininject.runtime)
                    }
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "me.mauricee.lama"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}