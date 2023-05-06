package com.tfandkusu.kgs.feature.detail

import com.tfandkusu.kgs.feature.viewmodel.StateEffect
import com.tfandkusu.kgs.model.GithubRepo
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class DetailReducerTest {

    private val reducer = DetailReducer()

    private val repo = GithubRepo(
        517191221,
        "DroidKaigi/conference-app-2022",
        "DroidKaigi",
        "https://avatars.githubusercontent.com/u/10727543?v=4",
        "Kotlin",
        460,
        460,
        194,
        39,
    )

    @Test
    fun initialState() {
        reducer.createInitialState().repo shouldBe null
    }

    @Test
    fun updateKeyword() {
        reducer.reduce(
            DetailState(),
            DetailAction.ShowGitHubRepo(repo),
        ) shouldBe StateEffect(DetailState(repo = repo))
    }
}
