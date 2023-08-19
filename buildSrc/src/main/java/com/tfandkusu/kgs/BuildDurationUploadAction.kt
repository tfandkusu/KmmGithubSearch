package com.tfandkusu.kgs

import com.google.cloud.Timestamp
import com.google.cloud.bigquery.BigQueryOptions
import com.google.cloud.bigquery.InsertAllRequest
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
        val bigquery = BigQueryOptions.newBuilder().setProjectId(PROJECT_ID).build().service
        val timestamp = Timestamp.now().toString()
        val duration = parameters.getBuildDuration().get()
        val type = "incremental"
        val content = mapOf<String, Any>(
            "created_at" to timestamp,
            "duration" to duration,
            "type" to type
        )
        val request = InsertAllRequest.newBuilder(
            DATASET_NAME,
            TABLE_NAME
        ).addRow(content).build()
        val response = bigquery.insertAll(request)
        if (response.hasErrors()) {
            throw RuntimeException(response.insertErrors.toString())
        }
    }

    companion object {
        const val PROJECT_ID = "tfandkusu"
        const val DATASET_NAME = "kmm_github_search"
        const val TABLE_NAME = "build_time"
    }
}
