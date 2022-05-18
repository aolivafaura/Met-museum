package com.aoliva.metmuseum.common.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider

abstract class BaseViewModel: ViewModel() {

    /**
     * Dispatcher provider. Coroutines launched from the view model must use this provider instead
     * of using Dispatchers.* directly.
     */
    protected abstract val dispatchers: DispatcherProvider

    /**
     * Defined to force viewmodels to get this field on constructor or as an injected property
     */
    protected abstract val savedStateHandle: SavedStateHandle
}