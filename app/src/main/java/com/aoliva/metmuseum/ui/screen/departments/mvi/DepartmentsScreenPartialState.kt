package com.aoliva.metmuseum.ui.screen.departments.mvi

import com.aoliva.metmuseum.common.mvi.PartialState
import com.aoliva.metmuseum.ui.screen.departments.model.DepartmentUi

sealed class DepartmentsScreenPartialState : PartialState {
    object Loading : DepartmentsScreenPartialState()
    object Error : DepartmentsScreenPartialState()
    class Success(val data: List<DepartmentUi>) : DepartmentsScreenPartialState()
}