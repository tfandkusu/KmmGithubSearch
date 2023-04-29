package com.tfandkusu.kgs.home

import com.tfandkusu.kgs.feature.ReduxViewModel
import com.tfandkusu.kgs.feature.home.HomeAction
import com.tfandkusu.kgs.feature.home.HomeActionCreator
import com.tfandkusu.kgs.feature.home.HomeEffect
import com.tfandkusu.kgs.feature.home.HomeEvent
import com.tfandkusu.kgs.feature.home.HomeReducer
import com.tfandkusu.kgs.feature.home.HomeState

class HomeViewModel(
    actionCreator: HomeActionCreator,
    reducer: HomeReducer,
) : ReduxViewModel<HomeEvent, HomeAction, HomeState, HomeEffect>(actionCreator, reducer)
