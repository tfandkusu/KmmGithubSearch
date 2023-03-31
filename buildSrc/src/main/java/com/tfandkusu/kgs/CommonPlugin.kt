package com.tfandkusu.kgs

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CommonPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.findByType(BaseExtension::class.java)?.let {
            it.compileSdkVersion(33)
            it.defaultConfig.minSdk = 21
            it.defaultConfig.targetSdk = 33
        }
        project.extensions.findByType(KotlinMultiplatformExtension::class.java)?.let {
            it.android().compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }
    }
}
