package com.aoliva.metmuseum.ui.screen.department.mvi

import com.aoliva.metmuseum.common.mvi.ViewEffect

sealed class DepartmentScreenViewEffect : ViewEffect {
    class NavigateToObjectDetail(val id: Int) : DepartmentScreenViewEffect()
}