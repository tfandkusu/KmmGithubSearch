@file:Suppress("UnusedImport")
import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp").version("1.8.0-1.0.9")
    id("org.kodein.mock.mockmp") version "1.12.0"
}
apply<CommonPlugin>()

android {
    namespace = "com.tfandkusu.kgs.feature.home"
}
mockmp {
    usesHelper = true
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(project(":data:remote"))
            }
        }
    }
}
