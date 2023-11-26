@file:Suppress("UnusedImport")

import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp")
    // id("com.rickclephas.kmp.nativecoroutines")
    id("org.jetbrains.kotlinx.kover")
}
apply<CommonPlugin>()

android {
    namespace = "com.tfandkusu.kgs.feature.home"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":common"))
            implementation(project(":data:remote"))
        }
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
    }
}
