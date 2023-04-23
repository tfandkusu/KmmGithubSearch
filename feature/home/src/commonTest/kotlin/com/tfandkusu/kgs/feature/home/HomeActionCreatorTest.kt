package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.data.remote.GithubRemoteDataSource
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeActionCreatorTest : TestsWithMocks() {
    /**
     * injectMocks は KSP によって作られる
     */
    override fun setUpMocks() = injectMocks(mocker) //(1)

    @Mock
    lateinit var dispatcher: Dispatcher<HomeAction>

    @Mock
    lateinit var remoteDataSource: GithubRemoteDataSource

    private val actionCreator by withMocks {
        HomeActionCreator(remoteDataSource)
    }

    @BeforeTest
    fun setUp() = runBlocking {
        everySuspending {
            dispatcher.dispatch(isAny())
        } returns Unit
    }

    @Test
    fun inputKeyword() = runTest {
        actionCreator.event(HomeEvent.InputKeyword("Kotlin"), dispatcher)
        verifyWithSuspend {
            dispatcher.dispatch(HomeAction.UpdateKeyword("Kotlin"))
        }
    }
}
