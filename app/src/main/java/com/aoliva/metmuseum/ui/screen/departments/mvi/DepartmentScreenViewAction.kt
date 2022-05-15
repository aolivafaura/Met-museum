package com.aoliva.metmuseum.ui.screen.departments.mvi

import com.aoliva.metmuseum.common.mvi.ViewAction

sealed class DepartmentScreenViewAction : ViewAction {

    class OnDepartmentClick(val id: Int) : DepartmentScreenViewAction()
}