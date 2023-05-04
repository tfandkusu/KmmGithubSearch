pluginManagement {
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

rootProject.name = "KmmGithubSearch"
enableFeaturePreview("VERSION_CATALOGS")
include(":androidApp")
include(":common")
include(":data:remote")
include(":feature:home")
include(":kgsios")
