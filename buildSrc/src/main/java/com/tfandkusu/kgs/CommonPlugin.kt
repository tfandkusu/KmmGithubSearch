package com.tfandkusu.kgs

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CommonPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        setUpAndrood(project)
        setUpKMM(project)
    }

    /**
     * Androidの設定
     */
    private fun setUpAndrood(project: Project) {
        project.extensions.configure<BaseExtension> {
            compileSdkVersion(34)
            defaultConfig.minSdk = 21
            defaultConfig.targetSdk = 34
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }

    /**
     * Kotlin Multiplatform Mobileの設定
     */
    private fun setUpKMM(project: Project) {
        project.extensions.findByType(KotlinMultiplatformExtension::class.java)?.let {
            // Androidの設定
            it.android().compilations.all {
                kotlinOptions {
                    jvmTarget = "17"
                }
            }
            // jvm向けビルドを作れるようにする
            it.jvm()
            // iOS向けビルドを作れるようにする
            listOf(
                it.iosX64(),
                it.iosArm64(),
                it.iosSimulatorArm64(),
            ).forEach { target ->
                target.binaries.framework {
                    baseName = project.name
                }
            }
            // commonMainの設定
            it.sourceSets.getByName("commonMain").dependencies {
                // 全モジュールで共通して使うライブラリ
                libs(project, "coroutine")?.let {
                    implementation(it)
                }
                libs(project, "napier")?.let {
                    implementation(it)
                }
                libs(project, "koin.core")?.let {
                    implementation(it)
                }
                libs(project, "kotlinx.datetime")?.let {
                    implementation(it)
                }
            }
            // commonTestの設定
            it.sourceSets.getByName("commonTest").dependencies {
                implementation(kotlin("test"))
                libs(project, "kotest.assertions")?.let {
                    implementation(it)
                }
                libs(project, "kotlin.coroutines.test")?.let {
                    implementation(it)
                }
            }
            // jvmTestの設定
            it.sourceSets.getByName("jvmTest").dependencies {
                implementation(kotlin("test"))
                libs(project, "kotest.assertions")?.let {
                    implementation(it)
                }
                libs(project, "kotlin.coroutines.test")?.let {
                    implementation(it)
                }
                libs(project, "mockk")?.let {
                    implementation(it)
                }
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

    /**
     * libs.versions.tomlからライブラリの情報を得る。
     *
     * 参考 https://github.com/android/nowinandroid/blob/main/build-logic/convention/src/main/kotlin/AndroidHiltConventionPlugin.kt
     */
    private fun libs(project: Project, name: String): Provider<MinimalExternalModuleDependency>? {
        return project.extensions.findByType(
            VersionCatalogsExtension::class.java,
        )?.named("libs")?.findLibrary(name)?.get()
    }
}
