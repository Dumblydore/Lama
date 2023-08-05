pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Lama"
include(":androidApp")
include(":iosApp")
include(":shared")
include(":domain")
include(":api:zen")
include(":feature:login")
include(":common:ui")
include(":feature:root")
include(":core:base")
include(":core:logging")
include(":common:resource:strings")
