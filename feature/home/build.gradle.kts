@file:Suppress("UnusedImport")

import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
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
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.test)
                implementation(libs.mockk)
                implementation(libs.kotest.assertions)
            }
        }
    }
}
