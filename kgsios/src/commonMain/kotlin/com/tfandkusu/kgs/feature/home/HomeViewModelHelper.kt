package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.feature.IosViewModelHelperBase
import org.koin.core.component.inject

class HomeViewModelHelper : IosViewModelHelperBase<HomeEvent, HomeAction, HomeState, HomeEffect>() {
    override val actionCreator: HomeActionCreator by inject()

    override val reducer: HomeReducer by inject()

}
