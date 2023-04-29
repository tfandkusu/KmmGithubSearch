package com.tfandkusu.kgs.feature.viewmodel

interface ActionCreator<EVENT, ACTION> {
    /**
     * Receive event and dispatch actions
     *
     * @param event
     * @param dispatcher
     */
    suspend fun event(event: EVENT, dispatcher: Dispatcher<ACTION>)
}
