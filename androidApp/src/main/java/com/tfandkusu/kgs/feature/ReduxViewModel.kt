package com.tfandkusu.kgs.feature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfandkusu.kgs.feature.viewmodel.ActionCreator
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import com.tfandkusu.kgs.feature.viewmodel.Reducer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class ReduxViewModel<EVENT, ACTION, STATE, EFFECT>(
    private val actionCreator: ActionCreator<EVENT, ACTION>,
    private val reducer: Reducer<ACTION, STATE, EFFECT>,
) : ViewModel() {
    private val _state = mutableStateOf(reducer.createInitialState())

    val state: State<STATE> = _state

    private val channel = Channel<EFFECT>(Channel.UNLIMITED)

    val effect: Flow<EFFECT>
        get() = channel.receiveAsFlow()

    fun event(event: EVENT) {
        viewModelScope.launch {
            actionCreator.event(
                event,
                object : Dispatcher<ACTION> {
                    override suspend fun dispatch(action: ACTION) {
                        state.value?.let { currentState ->
                            val stateEffect = reducer.reduce(currentState, action)
                            // Set next state
                            _state.value = stateEffect.state
                            // Set effect
                            stateEffect.effect?.let { effect ->
                                channel.send(effect)
                            }
                        }
                    }
                },
            )
        }
    }
}
