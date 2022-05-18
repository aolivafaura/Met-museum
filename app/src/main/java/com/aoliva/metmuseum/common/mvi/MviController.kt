package com.aoliva.metmuseum.common.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Defines the necessary functions and objects to implement by an MVI controller class.
 * The more natural way to implement the controller is by implementing it in a view model, but this
 * interface gives the freedom to implement the controller in a isolated way
 */
interface MviController<S : ViewState, P : PartialState, A : ViewAction, E : ViewEffect> {

    /**
     * Scope for [state] and [viewEffect] flows
     */
    val scope: CoroutineScope

    /**
     * View state flow
     */
    val state: StateFlow<S>

    /**
     * View effect flow
     */
    val viewEffect: SharedFlow<E>

    /**
     * Defines how to process an [action] received from the view
     */
    fun processAction(action: A)

    /**
     * Defines how to reduce [oldViewState] and [partialState]
     * @return [S] reduced state
     */
    fun reduceViewState(oldViewState: S, partialState: P): S

    /**
     * Defines how to process a [viewEffect]
     */
    fun processViewEffect(viewEffect: E)
}