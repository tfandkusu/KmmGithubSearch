package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.data.remote.GithubRemoteDataSource
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import io.mockk.MockKAnnotations
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomeActionCreatorTest {

    @MockK
    private lateinit var githubRemoteDataSource: GithubRemoteDataSource

    @MockK(relaxUnitFun = true)
    private lateinit var dispatcher: Dispatcher<HomeAction>

    private lateinit var actionCreator: HomeActionCreator

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        actionCreator = HomeActionCreator(githubRemoteDataSource)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun inputKeyword() = runTest {
        actionCreator.event(HomeEvent.InputKeyword("Kotlin"), dispatcher)
        coVerifySequence {
            dispatcher.dispatch(HomeAction.UpdateKeyword("Kotlin"))
        }
    }
}
