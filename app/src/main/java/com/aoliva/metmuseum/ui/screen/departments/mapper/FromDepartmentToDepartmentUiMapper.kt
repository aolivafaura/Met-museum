package com.aoliva.metmuseum.ui.screen.departments.mapper

import com.aoliva.metmuseum.domain.model.Department
import com.aoliva.metmuseum.ui.screen.departments.model.DepartmentUi
import javax.inject.Inject

class FromDepartmentToDepartmentUiMapper @Inject constructor() {

    fun map(departments: List<Department>): List<DepartmentUi> =
        departments.map { department -> DepartmentUi(id = department.id, name = department.name) }
}
