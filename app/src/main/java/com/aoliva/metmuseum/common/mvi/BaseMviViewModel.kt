package com.aoliva.metmuseum.common.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.common.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseMviViewModel<S : ViewState, P : PartialState, A : ViewAction, E : ViewEffect>(
    initialState: S
) : BaseViewModel(), MviController<S, P, A, E> {

    abstract override val dispatchers: DispatcherProvider

    abstract override val savedStateHandle: SavedStateHandle

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    private val _viewEffect = MutableSharedFlow<E>()
    val viewEffect: SharedFlow<E> = _viewEffect

    abstract override fun processAction(action: A)

    abstract override fun reduceViewState(oldViewState: S, partialState: P): S

    abstract override fun processViewEffect(viewEffect: E)

    protected fun emitState(newState: S) {
        viewModelScope.launch(dispatchers.main) {
            _state.emit(newState)
        }
    }

    protected fun reduceAndEmitNewState(oldViewState: S, partialState: P) {
        viewModelScope.launch(dispatchers.main) {
            _state.emit(reduceViewState(oldViewState, partialState))
        }
    }

    protected fun emitViewEffect(viewEffect: E) {
        viewModelScope.launch(dispatchers.main) {
            _viewEffect.emit(viewEffect)
        }
    }
}