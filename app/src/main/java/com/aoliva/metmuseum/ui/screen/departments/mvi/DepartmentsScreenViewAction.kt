package com.aoliva.metmuseum.ui.screen.departments.mvi

import com.aoliva.metmuseum.common.mvi.ViewAction

sealed class DepartmentsScreenViewAction : ViewAction {

    class OnDepartmentsClick(val id: Int) : DepartmentsScreenViewAction()
}