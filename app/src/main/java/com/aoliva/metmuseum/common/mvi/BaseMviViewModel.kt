package com.aoliva.metmuseum.common.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.common.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *
 */
abstract class BaseMviViewModel<S : ViewState, P : PartialState, A : ViewAction, E : ViewEffect>(
    initialState: S
) : BaseViewModel(), MviController<S, P, A, E> {

    override val scope: CoroutineScope = viewModelScope

    abstract override val dispatchers: DispatcherProvider

    abstract override val savedStateHandle: SavedStateHandle

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<S> = _state

    private val _viewEffect = MutableSharedFlow<E>()
    override val viewEffect: SharedFlow<E> = _viewEffect

    abstract override fun processAction(action: A)

    abstract override fun reduceViewState(oldViewState: S, partialState: P): S

    abstract override fun processViewEffect(viewEffect: E)

    /**
     * Emits the received [state]
     */
    protected fun emitState(state: S) {
        viewModelScope.launch(dispatchers.main) {
            _state.emit(state)
        }
    }

    /**
     * Generates a new state by reducing [oldViewState] and [partialState], and emits it.
     */
    protected fun reduceAndEmitNewState(oldViewState: S, partialState: P) {
        viewModelScope.launch(dispatchers.main) {
            _state.emit(reduceViewState(oldViewState, partialState))
        }
    }

    /**
     * Emits the received [viewEffect]
     */
    protected fun emitViewEffect(viewEffect: E) {
        viewModelScope.launch(dispatchers.main) {
            _viewEffect.emit(viewEffect)
        }
    }
}