package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.Context
import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.feature.viewmodel.ActionCreator
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import io.github.aakira.napier.Napier
import kotlin.math.min

class HomeActionCreator(
    private val searchGithub: SearchGithubUseCase,
) : ActionCreator<HomeEvent, HomeAction> {
    override suspend fun event(event: HomeEvent, dispatcher: Dispatcher<HomeAction>) {
        when (event) {
            is HomeEvent.InputKeyword -> {
                val oneLine = event.keyword.replace("\n", "")
                val substring = oneLine.substring(0, min(50, oneLine.length))
                dispatcher.dispatch(
                    HomeAction.UpdateKeyword(
                        substring,
                    ),
                )
            }

            is HomeEvent.SearchKeyword -> {
                dispatcher.dispatch(HomeAction.HideKeyboard)
                if (event.keyword.trim().isEmpty()) {
                    return
                }
                dispatcher.dispatch(HomeAction.StartSearch)
                try {
                    val githubRepoList = searchGithub(event.keyword.trim())
                    dispatcher.dispatch(HomeAction.UpdateList(githubRepoList))
                } catch (e: MyError) {
                    dispatcher.dispatch(HomeAction.Error(e))
                    Napier.d("MyError", e)
                }
            }
        }
    }

    private fun useContext(context: Context, i: Int) {
    }
}
