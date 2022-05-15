package com.aoliva.metmuseum.common.mvi

interface ViewActionProcessor<T: ViewAction> {

    fun processAction(action: T)
}