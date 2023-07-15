@file:Suppress("UnusedImport")

import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp") version "1.8.22-1.0.11"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-12"
    id("org.jetbrains.kotlinx.kover")
}
apply<CommonPlugin>()

android {
    namespace = "com.tfandkusu.kgs.feature.home"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(project(":data:remote"))
            }
        }
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
    }
}
