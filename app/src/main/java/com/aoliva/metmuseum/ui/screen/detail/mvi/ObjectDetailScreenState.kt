package com.aoliva.metmuseum.ui.screen.detail.mvi

import androidx.compose.runtime.Immutable
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.mvi.ViewState
import com.aoliva.metmuseum.ui.screen.detail.model.MetObjectDetailUi

@Immutable
data class ObjectDetailScreenState(val dataState: LoadingErrorSuccess<MetObjectDetailUi>) :
    ViewState {

    companion object {
        val INITIAL = ObjectDetailScreenState(LoadingErrorSuccess.Loading())
    }
}