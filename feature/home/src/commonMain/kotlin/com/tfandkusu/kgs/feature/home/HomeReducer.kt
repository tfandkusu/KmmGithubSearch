package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.feature.viewmodel.Reducer
import com.tfandkusu.kgs.feature.viewmodel.StateEffect

class HomeReducer : Reducer<HomeAction, HomeState, HomeEffect> {
    override fun reduce(
        state: HomeState,
        action: HomeAction,
    ): StateEffect<HomeState, HomeEffect> {
        return when (action) {
            is HomeAction.UpdateKeyword -> {
                StateEffect(
                    state.copy(
                        keyword = action.keyword,
                    ),
                )
            }

            HomeAction.StartSearch -> {
                StateEffect(
                    state.copy(
                        items = listOf(HomeState.Item.Progress),
                    ),
                    HomeEffect.HideKeyboard,
                )
            }

            is HomeAction.UpdateList -> {
                StateEffect(
                    state.copy(
                        items = action.repos.map {
                            HomeState.Item.Repo(it)
                        },
                    ),
                )
            }

            is HomeAction.Error -> {
                when (action.e) {
                    MyError.Network -> {
                        StateEffect(
                            state.copy(
                                items = listOf(HomeState.Item.NetworkError),
                            ),
                        )
                    }

                    is MyError.Server -> {
                        StateEffect(
                            state.copy(
                                items = listOf(HomeState.Item.ServerError(action.e.statusCode)),
                            ),
                        )
                    }
                }
            }
        }
    }

    override fun createInitialState(): HomeState {
        return HomeState()
    }
}
