package com.tfandkusu.kgs.feature.detail

import com.tfandkusu.kgs.feature.viewmodel.Reducer
import com.tfandkusu.kgs.feature.viewmodel.StateEffect

class DetailReducer : Reducer<DetailAction, DetailState, DetailEffect> {
    override fun createInitialState(): DetailState {
        return DetailState()
    }

    override fun reduce(
        state: DetailState,
        action: DetailAction,
    ): StateEffect<DetailState, DetailEffect> {
        when (action) {
            is DetailAction.ShowGitHubRepo -> {
                return StateEffect(
                    state.copy(repo = action.repo),
                )
            }
        }
    }
}
