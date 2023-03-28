package com.tfandkusu.kgs

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class CommonPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.findByType(BaseExtension::class.java)
        extension?.compileSdkVersion(33)
        extension?.defaultConfig?.minSdk = 21
        extension?.defaultConfig?.targetSdk = 33
    }
}
