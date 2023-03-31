package com.tfandkusu.kgs

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CommonPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Androidの設定
        project.extensions.findByType(BaseExtension::class.java)?.let {
            it.compileSdkVersion(33)
            it.defaultConfig.minSdk = 21
            it.defaultConfig.targetSdk = 33
        }
        project.extensions.findByType(KotlinMultiplatformExtension::class.java)?.let {
            // Androidの設定
            it.android().compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
            // iOS向けビルドを作れるようにする
            listOf(
                it.iosX64(),
                it.iosArm64(),
                it.iosSimulatorArm64()
            ).forEach {
                it.binaries.framework {
                    baseName = project.name
                }
            }
            // commonTestの設定
            it.sourceSets.getByName("commonTest").dependencies {
                implementation(kotlin("test"))
            }
            // iOSの設定
            it.sourceSets.create("iosMain").dependsOn(it.sourceSets.getByName("commonMain"))
            val iosMain = it.sourceSets.getByName("iosMain")
            it.sourceSets.getByName("iosX64Main").dependsOn(iosMain)
            it.sourceSets.getByName("iosArm64Main").dependsOn(iosMain)
            it.sourceSets.getByName("iosSimulatorArm64Main").dependsOn(iosMain)
            // iOSのテストの設定
            it.sourceSets.create("iosTest").dependsOn(it.sourceSets.getByName("commonTest"))
            val iosTest = it.sourceSets.getByName("iosTest")
            it.sourceSets.getByName("iosX64Test").dependsOn(iosTest)
            it.sourceSets.getByName("iosArm64Test").dependsOn(iosTest)
            it.sourceSets.getByName("iosSimulatorArm64Test").dependsOn(iosTest)
        }
    }
}
