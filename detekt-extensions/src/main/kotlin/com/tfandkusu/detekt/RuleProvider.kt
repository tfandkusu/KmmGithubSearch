package com.tfandkusu.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class RuleProvider : RuleSetProvider {
    override val ruleSetId: String = "extra-rules"

    override fun instance(config: Config): RuleSet = RuleSet(ruleSetId, emptyList())
}