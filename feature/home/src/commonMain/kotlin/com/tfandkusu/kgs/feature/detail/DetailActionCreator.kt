package com.tfandkusu.kgs.feature.detail

import com.tfandkusu.kgs.feature.viewmodel.ActionCreator
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher

class DetailActionCreator : ActionCreator<DetailEvent, DetailAction> {
    override suspend fun event(event: DetailEvent, dispatcher: Dispatcher<DetailAction>) {
        when (event) {
            is DetailEvent.OnCreate -> {
                dispatcher.dispatch(DetailAction.ShowGitHubRepo(event.repo))
            }
        }
    }
}
