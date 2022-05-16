package com.aoliva.metmuseum.ui.screen.departments.mvi

import com.aoliva.metmuseum.common.mvi.ViewEffect

sealed class DepartmentsScreenViewEffect : ViewEffect {
    class NavigateToDepartment(val departmentId: Int) : DepartmentsScreenViewEffect()
}