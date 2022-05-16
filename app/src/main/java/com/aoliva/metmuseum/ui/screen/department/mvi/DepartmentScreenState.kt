package com.aoliva.metmuseum.ui.screen.department.mvi

import androidx.compose.runtime.Immutable
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.mvi.ViewState
import com.aoliva.metmuseum.ui.screen.department.model.MetObjectListItemUi

@Immutable
data class DepartmentScreenState(val dataState: LoadingErrorSuccess<List<MetObjectListItemUi>>) :
    ViewState {

    companion object {
        val INITIAL = DepartmentScreenState(LoadingErrorSuccess.Loading())
    }
}