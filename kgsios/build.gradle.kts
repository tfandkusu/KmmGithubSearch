@file:Suppress("UnusedImport")

import co.touchlab.skie.configuration.FlowInterop
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlinx.kover")
    id("co.touchlab.skie")
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
        commonMain.dependencies {
            api(project(":feature:home"))
            api(project(":common"))
            implementation(libs.coroutine)
            implementation(libs.napier)
            implementation(libs.koin.core)
        }
        jvm()
        jvmTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.konsist)
        }
    }
}

