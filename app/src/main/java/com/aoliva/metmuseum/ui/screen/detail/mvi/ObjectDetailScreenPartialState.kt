package com.aoliva.metmuseum.ui.screen.detail.mvi

import com.aoliva.metmuseum.common.mvi.PartialState
import com.aoliva.metmuseum.ui.screen.detail.model.MetObjectDetailUi

sealed class ObjectDetailScreenPartialState : PartialState {
    object Loading : ObjectDetailScreenPartialState()
    object Error : ObjectDetailScreenPartialState()
    class Success(val data: MetObjectDetailUi) : ObjectDetailScreenPartialState()
}