package com.tfandkusu.kgs.feature.viewmodel

interface Dispatcher<ACTION> {
    /**
     * Called in ActionCreator to dispatch action.
     */
    suspend fun dispatch(action: ACTION)
}
