package com.tfandkusu.kgs.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfandkusu.kgs.feature.viewmodel.ActionCreator
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import com.tfandkusu.kgs.feature.viewmodel.Reducer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface ReduxViewModel<EVENT, STATE, EFFECT> {
    val state: State<STATE>

    val effect: Flow<EFFECT>

    fun event(event: EVENT)
}

abstract class ReduxViewModelImpl<EVENT, ACTION, STATE, EFFECT>(
    private val actionCreator: ActionCreator<EVENT, ACTION>,
    private val reducer: Reducer<ACTION, STATE, EFFECT>,
) : ReduxViewModel<EVENT, STATE, EFFECT>, ViewModel() {
    private val _state = mutableStateOf(reducer.createInitialState())

    override val state: State<STATE> = _state

    private val channel = Channel<EFFECT>(Channel.UNLIMITED)

    override val effect: Flow<EFFECT>
        get() = channel.receiveAsFlow()

    override fun event(event: EVENT) {
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

data class StateEffectDispatch<STATE, EFFECT, EVENT>(
    val state: STATE,
    val effectFlow: Flow<EFFECT>,
    val dispatch: (EVENT) -> Unit,
)

@Composable
inline fun <reified STATE, EFFECT, EVENT> use(
    viewModel: ReduxViewModel<EVENT, STATE, EFFECT>,
): StateEffectDispatch<STATE, EFFECT, EVENT> {
    val state = viewModel.state.value
    val dispatch = remember(viewModel) {
        { event: EVENT ->
            viewModel.event(event)
        }
    }
    return StateEffectDispatch(
        state = state,
        effectFlow = viewModel.effect,
        dispatch = dispatch,
    )
}
