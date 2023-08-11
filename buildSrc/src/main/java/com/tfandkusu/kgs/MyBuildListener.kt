package com.tfandkusu.kgs

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Plugin
import org.gradle.api.flow.FlowProviders
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import javax.inject.Inject

class MyBuildListener : BuildListener {

    private var startTime = 0L

    override fun settingsEvaluated(settings: Settings) {
    }

    override fun projectsLoaded(gradle: Gradle) {
    }

    override fun projectsEvaluated(gradle: Gradle) {
        startTime = System.currentTimeMillis()
    }

    override fun buildFinished(result: BuildResult) {
        val duration = System.currentTimeMillis() - startTime
        println("Build finished in ${duration}ms")
    }
}
