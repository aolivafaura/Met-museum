package com.aoliva.metmuseum.ui.screen.department.mvi

import com.aoliva.metmuseum.common.mvi.ViewAction

sealed class DepartmentScreenViewAction : ViewAction {
    class OnItemClick(val id: Int) : DepartmentScreenViewAction()
}