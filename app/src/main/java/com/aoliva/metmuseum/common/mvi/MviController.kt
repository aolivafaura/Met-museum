package com.aoliva.metmuseum.common.mvi

interface MviController<S : ViewState, P : PartialState, A : ViewAction, E : ViewEffect> {

    fun processAction(action: A)

    fun reduceViewState(oldViewState: S, partialState: P): S

    fun processViewEffect(viewEffect: E)
}