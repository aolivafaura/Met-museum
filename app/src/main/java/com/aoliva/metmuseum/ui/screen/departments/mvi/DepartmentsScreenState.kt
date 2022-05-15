package com.aoliva.metmuseum.ui.screen.departments.mvi

import androidx.compose.runtime.Immutable
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.mvi.ViewState
import com.aoliva.metmuseum.ui.screen.departments.model.DepartmentUi

@Immutable
data class DepartmentsScreenState(val dataState: LoadingErrorSuccess<List<DepartmentUi>>) :
    ViewState {

    companion object {
        val INITIAL = DepartmentsScreenState(LoadingErrorSuccess.LoadingError())
    }
}