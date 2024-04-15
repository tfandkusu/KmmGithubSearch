package com.tfandkusu.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtNamedFunction

class ContextOrder(config: Config) : Rule(config) {

    companion object {
        const val CONTEXT = "Context"
        const val CONTEXT_WITH_QUESTION_MARK = "$CONTEXT?"
        const val REPORT_MESSAGE = "Context must be the first parameter"
        const val ISSUE_DESCRIPTION =
            "This rule reports the method which doesn't use Context as the first parameter"
    }

    override val issue = Issue(
        id = javaClass.simpleName,
        severity = Severity.Minor,
        description = ISSUE_DESCRIPTION,
        debt = Debt.FIVE_MINS,
    )

    override fun visitNamedFunction(func: KtNamedFunction) {
        super.visitNamedFunction(func)
        val parameters = func.valueParameters
        // If parameter size smaller than 2, no need to check
        if (parameters.size < 2) {
            return
        }
        // Get parameter types like [String,Context,Fragment] etc
        val parameterTypeList = parameters.map { x -> x.children[0].text }
        // If Context is the first parameter, no need to check
        if (isContext(parameterTypeList[0])) {
            return
        }
        // If Context is not the first parameter, report issue
        else if (isContext(parameterTypeList)) {
            report(CodeSmell(issue, Entity.from(func), REPORT_MESSAGE))
        }
    }

    private fun isContext(s: String?): Boolean {
        return (s == CONTEXT || s == CONTEXT_WITH_QUESTION_MARK)
    }

    private fun isContext(s: List<String>): Boolean {
        return (s.contains(CONTEXT) || s.contains(CONTEXT_WITH_QUESTION_MARK))
    }
}
