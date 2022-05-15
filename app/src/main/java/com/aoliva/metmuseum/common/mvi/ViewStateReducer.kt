package com.aoliva.metmuseum.common.mvi

interface ViewStateReducer<T: ViewState, P> {

    fun reduceViewState(oldViewState: T, partialState: P): T
}