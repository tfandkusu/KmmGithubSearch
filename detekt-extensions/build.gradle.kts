plugins {
    id("kotlin")
    alias(libs.plugins.detekt.gradle.plugin)
}
dependencies {
    compileOnly(libs.detekt.api)
}
