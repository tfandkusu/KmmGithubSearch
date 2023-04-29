package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.feature.viewmodel.StateEffect
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class HomeReducerTest {

    private val reducer = HomeReducer()

    @Test
    fun updateKeyword() {
        reducer.reduce(
            HomeState(),
            HomeAction.UpdateKeyword("kotlin"),
        ) shouldBe StateEffect(HomeState(keyword = "kotlin"))
    }
}
