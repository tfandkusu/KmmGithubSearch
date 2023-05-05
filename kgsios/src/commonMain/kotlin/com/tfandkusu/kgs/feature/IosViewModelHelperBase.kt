package com.tfandkusu.kgs.feature

import com.tfandkusu.kgs.feature.viewmodel.ActionCreator
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import com.tfandkusu.kgs.feature.viewmodel.Reducer
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

abstract class IosViewModelHelperBase<EVENT, ACTION, STATE, EFFECT> : KoinComponent {
    protected abstract val actionCreator: ActionCreator<EVENT, ACTION>

    protected abstract val reducer: Reducer<ACTION, STATE, EFFECT>

    private var state: STATE? = null

    private var updateState: (STATE) -> Unit = {}

    private val coroutineScope = MainScope()

    fun createInitialState(): STATE {
        return reducer.createInitialState()
    }

    fun setUp(updateState: (STATE) -> Unit) {
        val state = reducer.createInitialState()
        this.updateState = updateState
        this.state = state
    }

    fun clean() {
        this.coroutineScope.cancel()
        this.updateState = {}
    }

    fun event(event: EVENT) {
        coroutineScope.launch {
            actionCreator.event(
                event,
                object : Dispatcher<ACTION> {
                    override suspend fun dispatch(action: ACTION) {
                        state?.let { currentState ->
                            val stateEffect = reducer.reduce(currentState, action)
                            // Set next state
                            state = stateEffect.state
                            state?.let { nextState ->
                                updateState(nextState)
                            }
                            // TODO どのスレッドで動いているか確認する
                            // Set effect
                            // stateEffect.effect?.let { effect ->
                            //    channel.send(effect)
                            // }
                        }
                    }
                },
            )
        }
    }
}
