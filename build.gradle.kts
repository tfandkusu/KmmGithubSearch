plugins {
    id("org.jetbrains.kotlinx.kover") version "0.7.2"
}
dependencies {
    kover(project(":androidApp"))
}
