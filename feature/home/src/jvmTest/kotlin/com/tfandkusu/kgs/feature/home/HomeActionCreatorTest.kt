package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import com.tfandkusu.kgs.model.GithubRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeActionCreatorTest {

    @MockK
    private lateinit var searchGithub: SearchGithubUseCase

    @MockK(relaxUnitFun = true)
    private lateinit var dispatcher: Dispatcher<HomeAction>

    private lateinit var actionCreator: HomeActionCreator

    private val repos = listOf(
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
            Instant.parse("2023-05-13T13:24:03Z"),
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
            Instant.parse("2022-05-13T13:24:03Z"),
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
            Instant.parse("2021-05-13T13:24:03Z"),
        ),
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        actionCreator = HomeActionCreator(searchGithub)
    }

    @Test
    fun inputKeyword() = runTest {
        actionCreator.event(HomeEvent.InputKeyword("Kotlin"), dispatcher)
        coVerifySequence {
            dispatcher.dispatch(HomeAction.UpdateKeyword("Kotlin"))
        }
    }

    @Test
    fun inputKeywordOneLine() = runTest {
        actionCreator.event(HomeEvent.InputKeyword("one\ntwo\nthree"), dispatcher)
        coVerifySequence {
            dispatcher.dispatch(HomeAction.UpdateKeyword("onetwothree"))
        }
    }

    @Test
    fun inputKeywordLimitLength() = runTest {
        actionCreator.event(
            HomeEvent.InputKeyword(
                "aaaaa\nbbbbb" + (10 until 51).joinToString(separator = "") { "c" },
            ),
            dispatcher,
        )
        coVerifySequence {
            dispatcher.dispatch(
                HomeAction.UpdateKeyword(
                    "aaaaabbbbb" + (10 until 50).joinToString(separator = "") { "c" },
                ),
            )
        }
    }

    @Test
    fun searchSuccess() = runTest {
        coEvery {
            searchGithub("Kotlin")
        } returns repos
        actionCreator.event(HomeEvent.SearchKeyword("Kotlin"), dispatcher)
        coVerifySequence {
            dispatcher.dispatch(HomeAction.HideKeyboard)
            dispatcher.dispatch(HomeAction.StartSearch)
            searchGithub("Kotlin")
            dispatcher.dispatch(HomeAction.UpdateList(repos))
        }
    }

    @Test
    fun searchError() = runTest {
        coEvery {
            searchGithub("Kotlin")
        } throws MyError.Network
        actionCreator.event(HomeEvent.SearchKeyword(" Kotlin "), dispatcher)
        coVerifySequence {
            dispatcher.dispatch(HomeAction.HideKeyboard)
            dispatcher.dispatch(HomeAction.StartSearch)
            searchGithub("Kotlin")
            dispatcher.dispatch(HomeAction.Error(MyError.Network))
        }
    }

    @Test
    fun searchEmpty() = runTest {
        actionCreator.event(HomeEvent.SearchKeyword(" "), dispatcher)
        coVerifySequence {
            dispatcher.dispatch(HomeAction.HideKeyboard)
        }
    }
}
