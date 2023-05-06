package com.tfandkusu.kgs.feature.detail

import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import com.tfandkusu.kgs.model.GithubRepo
import io.mockk.MockKAnnotations
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailActionCreatorTest {

    @MockK(relaxUnitFun = true)
    private lateinit var dispatcher: Dispatcher<DetailAction>

    private lateinit var actionCreator: DetailActionCreator

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        actionCreator = DetailActionCreator()
    }

    @Test
    fun onCreate() = runTest {
        val repo = mockk<GithubRepo>()
        actionCreator.event(DetailEvent.OnCreate(repo), dispatcher)
        coVerifySequence {
            dispatcher.dispatch(DetailAction.ShowGitHubRepo(repo))
        }
    }
}
