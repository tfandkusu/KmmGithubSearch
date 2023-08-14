package com.tfandkusu.kgs

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.flow.FlowActionSpec
import org.gradle.api.flow.FlowProviders
import org.gradle.api.flow.FlowScope
import javax.inject.Inject

abstract class BuildDurationUploadPlugin : Plugin<Project> {

    @Inject
    protected abstract fun getFlowScope(): FlowScope

    @Inject
    protected abstract fun getFlowProviders(): FlowProviders

    /**
     * 開始時刻を記録
     */
    private val startTime = System.currentTimeMillis()

    override fun apply(project: Project) {
        val uploadBuildTimeProvider = project.providers.gradleProperty("uploadBuildTime")
        if(uploadBuildTimeProvider.orNull == "true") {
            // 参考にしたサンプルはこちら
            // ビルドが終わったら効果音を流すプラグイン
            // https://github.com/gradle/gradle/tree/master/subprojects/docs/src/snippets/dataflowActions/playSound/groovy/plugin/src/main/java/org/gradle/sample/sound
            // 今回は効果音を鳴らす部分を、ビルド時間を BigQuery へのアップロードに差し替える
            getFlowScope().always(
                BuildDurationUploadAction::class.java,
                object : Action<FlowActionSpec<BuildDurationUploadAction.Parameters>> {
                    override fun execute(spec: FlowActionSpec<BuildDurationUploadAction.Parameters>) {
                        spec.parameters.getBuildDuration().set(
                            getFlowProviders().buildWorkResult.map {
                                System.currentTimeMillis() - startTime
                            }
                        )
                    }
                }
            )
        }
    }
}
