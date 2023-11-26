plugins {
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
    id("org.jetbrains.kotlinx.kover") version "0.7.2"
    id("com.google.devtools.ksp") version "1.9.20-1.0.14"
    alias(libs.plugins.spotless.gradle.plugin)
    id("co.touchlab.skie") version "0.5.5"
}

koverReport {
    filters {
        includes {
            classes("*ActionCreator", "*Reducer", "*RemoteDataSourceImpl")
        }
    }
}

dependencies {
    kover(project(":feature:home"))
    kover(project(":data:remote"))
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint("0.50.0").userData(mapOf("android" to "true"))
    }
}
