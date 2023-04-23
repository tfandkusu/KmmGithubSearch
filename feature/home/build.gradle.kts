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
    }
}
