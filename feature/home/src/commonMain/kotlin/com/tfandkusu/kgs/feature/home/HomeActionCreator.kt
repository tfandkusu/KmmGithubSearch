package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.data.remote.GithubRemoteDataSource
import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.feature.viewmodel.ActionCreator
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher

class HomeActionCreator(
    private val remoteDataSource: GithubRemoteDataSource,
) : ActionCreator<HomeEvent, HomeAction> {
    override suspend fun event(event: HomeEvent, dispatcher: Dispatcher<HomeAction>) {
        when (event) {
            is HomeEvent.InputKeyword -> {
                dispatcher.dispatch(HomeAction.UpdateKeyword(event.keyword))
            }

            is HomeEvent.SearchKeyword -> {
                dispatcher.dispatch(HomeAction.StartSearch)
                try {
                    val githubRepoList = remoteDataSource.search(event.keyword)
                    dispatcher.dispatch(HomeAction.UpdateList(githubRepoList))
                } catch (e: MyError) {
                    dispatcher.dispatch(HomeAction.Error(e))
                }
            }
        }
    }
}
