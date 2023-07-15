@file:Suppress("UnusedImport")

import com.tfandkusu.kgs.CommonPlugin
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
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
