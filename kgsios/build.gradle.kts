@file:Suppress("UnusedImport")

import com.tfandkusu.kgs.CommonPlugin

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
apply<CommonPlugin>()

android {
    namespace = "com.tfandkusu.kgs.kgsios"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":feature:home"))
                implementation(project(":common"))
            }
        }
    }
}
