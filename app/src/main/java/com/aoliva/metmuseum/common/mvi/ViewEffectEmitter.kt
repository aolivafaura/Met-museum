package com.aoliva.metmuseum.common.mvi

interface ViewEffectEmitter<T: ViewEffect> {

    fun emitViewEffect(viewEffect: T)
}