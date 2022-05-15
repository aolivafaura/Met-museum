package com.aoliva.metmuseum.common.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider

abstract class BaseViewModel: ViewModel() {

    internal abstract val dispatchers: DispatcherProvider

    internal abstract val savedStateHandle: SavedStateHandle
}