@file:Suppress("UnusedImport")

import com.tfandkusu.kgs.CommonPlugin
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

android {
    namespace = "com.tfandkusu.kgs.kgsios"
    compileSdk = 33
    defaultConfig.minSdk = 21
    defaultConfig.targetSdk = 33
}

kotlin {
    sourceSets {
        android()
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                baseName = "kgsios"
                export(project(":feature:home"))
                export(project(":common"))
            }
        }
        val commonMain by getting {
            dependencies {
                api(project(":feature:home"))
                api(project(":common"))
                implementation(libs.coroutine)
                implementation(libs.napier)
                implementation(libs.koin.core)
            }
        }
    }
}
