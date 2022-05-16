package com.aoliva.metmuseum.ui.screen.department.mvi

import com.aoliva.metmuseum.common.mvi.PartialState
import com.aoliva.metmuseum.ui.screen.department.model.MetObjectListItemUi

sealed class DepartmentScreenPartialState : PartialState {
    object Loading : DepartmentScreenPartialState()
    object Error : DepartmentScreenPartialState()
    class Success(val data: List<MetObjectListItemUi>) : DepartmentScreenPartialState()
}