package com.tfandkusu.kgs

import org.gradle.api.flow.FlowAction
import org.gradle.api.flow.FlowParameters
import org.gradle.api.tasks.Input
import org.gradle.api.provider.Property;


class BuildDurationUploadAction : FlowAction<BuildDurationUploadAction.Parameters> {
    interface Parameters : FlowParameters {
        @Input
        fun getBuildDuration(): Property<Long>
    }

    override fun execute(parameters: Parameters) {
        println("Build duration = " + parameters.getBuildDuration().get())
    }
}
