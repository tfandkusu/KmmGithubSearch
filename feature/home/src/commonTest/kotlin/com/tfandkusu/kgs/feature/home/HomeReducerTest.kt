package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.feature.viewmodel.StateEffect
import com.tfandkusu.kgs.model.GithubRepo
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class HomeReducerTest {

    private val reducer = HomeReducer()

    @Test
    fun initialState() {
        reducer.createInitialState().keyword shouldBe ""
        reducer.createInitialState().items shouldBe listOf()
    }

    @Test
    fun updateKeyword() {
        reducer.reduce(
            HomeState(),
            HomeAction.UpdateKeyword("kotlin"),
        ) shouldBe StateEffect(HomeState(keyword = "kotlin"))
    }

    @Test
    fun startSearch() {
        reducer.reduce(
            HomeState(),
            HomeAction.StartSearch,
        ) shouldBe StateEffect(
            state = HomeState(items = listOf(HomeState.Item.Progress)),
            effect = HomeEffect.HideKeyboard,
        )
    }

    @Test
    fun updateList() {
        val repos = listOf(
            GithubRepo(
                517191221,
                "DroidKaigi/conference-app-2022",
                "DroidKaigi",
                "https://avatars.githubusercontent.com/u/10727543?v=4",
                "Kotlin",
                460,
                460,
                194,
                39,
            ),
            GithubRepo(
                283062475,
                "DroidKaigi/conference-app-2021",
                "DroidKaigi",
                "https://avatars.githubusercontent.com/u/10727543?v=4",
                "Kotlin",
                632,
                632,
                189,
                45,
            ),
            GithubRepo(
                202978106,
                "DroidKaigi/conference-app-2020",
                "DroidKaigi",
                "https://avatars.githubusercontent.com/u/10727543?v=4",
                "Kotlin",
                785,
                785,
                330,
                46,
            ),
        )

        reducer.reduce(
            HomeState(),
            HomeAction.UpdateList(repos),
        ) shouldBe StateEffect(
            HomeState(
                items = listOf(
                    HomeState.Item.Repo(repos[0]),
                    HomeState.Item.Repo(repos[1]),
                    HomeState.Item.Repo(repos[2]),
                ),
            ),
        )
    }

    @Test
    fun errorNetwork() {
        reducer.reduce(
            HomeState(),
            HomeAction.Error(MyError.Network),
        ) shouldBe StateEffect(
            HomeState(
                items = listOf(
                    HomeState.Item.NetworkError,
                ),
            ),
        )
    }

    @Test
    fun errorServer() {
        reducer.reduce(
            HomeState(),
            HomeAction.Error(MyError.Server(500)),
        ) shouldBe StateEffect(
            HomeState(
                items = listOf(
                    HomeState.Item.ServerError(500),
                ),
            ),
        )
    }
}
