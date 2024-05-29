package com.tfandkusu.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.resolve.calls.util.getCalleeExpressionIfAny

class TrackScreenEvent(config: Config) : Rule(config) {
    companion object {
        const val ISSUE_DESCRIPTION = "画面遷移のイベントを送っていないです。TrackScreenEvent 関数を呼び出してください。"
        const val REPORT_MESSAGE = ISSUE_DESCRIPTION
    }

    override val issue = Issue(
        id = javaClass.simpleName,
        severity = Severity.Minor,
        description = ISSUE_DESCRIPTION,
        debt = Debt.FIVE_MINS,
    )

    /**
     * TrackScreen 関数が呼ばれたら true にする。
     */
    private var trackScreenIsCalled = false

    /**
     * 構文木から関数を見つけたときに呼ばれる。
     */
    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        // TrackScreen 関数を除く Screen で終わる Composable 関数で
        if (function.name?.endsWith("Screen") == true && isComposable(function)
        ) {
            // TrackScreen メソッドが呼ばれていなかったら報告する。
            if (!trackScreenIsCalled) {
                report(CodeSmell(issue, Entity.from(function), REPORT_MESSAGE))
            }
        }
        // 関数が見つかったら TrackScreen 関数の呼び出し状態をリセットする。
        trackScreenIsCalled = false
    }

    /**
     * 構文木から関数呼び出しを見つけたときに呼ばれる。
     * 関数呼び出しの方が深いので visitNamedFunction よりも先に呼ばれる。
     */
    override fun visitCallExpression(expression: KtCallExpression) {
        super.visitCallExpression(expression)
        expression.getCalleeExpressionIfAny().let { func ->
            if (func?.text == "TrackScreenEvent") {
                trackScreenIsCalled = true
            }
        }
    }

    private fun isComposable(function: KtNamedFunction): Boolean {
        for (annotation in function.annotationEntries) {
            if (annotation.text.contains("Composable")) {
                return true
            }
        }
        return false
    }
}
