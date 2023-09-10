package com.tfandkusu.kgs.feature.detail

import com.tfandkusu.kgs.feature.IosViewModelHelperBase
import org.koin.core.component.inject

class DetailViewModelHelper : IosViewModelHelperBase<
    DetailEvent,
    DetailAction,
    DetailState,
    DetailEffect,
    >() {
    override val actionCreator: DetailActionCreator by inject()
    override val reducer: DetailReducer by inject()
}
