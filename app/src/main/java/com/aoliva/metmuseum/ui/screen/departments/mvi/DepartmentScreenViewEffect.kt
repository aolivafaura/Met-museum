package com.aoliva.metmuseum.ui.screen.departments.mvi

import com.aoliva.metmuseum.common.mvi.ViewEffect

sealed class DepartmentScreenViewEffect : ViewEffect {
    class NavigateToObjects(val departmentId: Int) : DepartmentScreenViewEffect()
}