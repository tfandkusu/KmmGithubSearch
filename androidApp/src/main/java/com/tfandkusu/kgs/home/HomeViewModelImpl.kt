package com.tfandkusu.kgs.home

import com.tfandkusu.kgs.feature.ReduxViewModel
import com.tfandkusu.kgs.feature.ReduxViewModelImpl
import com.tfandkusu.kgs.feature.home.HomeAction
import com.tfandkusu.kgs.feature.home.HomeActionCreator
import com.tfandkusu.kgs.feature.home.HomeEffect
import com.tfandkusu.kgs.feature.home.HomeEvent
import com.tfandkusu.kgs.feature.home.HomeReducer
import com.tfandkusu.kgs.feature.home.HomeState

interface HomeViewModel : ReduxViewModel<HomeEvent, HomeState, HomeEffect>

class HomeViewModelImpl(
    actionCreator: HomeActionCreator,
    reducer: HomeReducer,
) : HomeViewModel,
    ReduxViewModelImpl<HomeEvent, HomeAction, HomeState, HomeEffect>(
        actionCreator,
        reducer,
    )
