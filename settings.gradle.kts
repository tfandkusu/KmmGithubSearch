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
include(":androidApp")
include(":common")
include(":data:remote")
include(":feature:home")
include(":kgsios")
