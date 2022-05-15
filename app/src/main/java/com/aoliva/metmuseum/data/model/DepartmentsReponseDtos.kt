package com.aoliva.metmuseum.data.model

import com.aoliva.metmuseum.domain.model.Department
import com.squareup.moshi.Json

/**
 * @see [Met Museum]()https://metmuseum.github.io/) docs for further info
 */
data class DepartmentsResponseDto(
    @Json(name = "departments") val departments: List<DepartmentDto>
)

/**
 * @see [Met Museum]()https://metmuseum.github.io/) docs for further info
 */
data class DepartmentDto(val departmentId: Int, val displayName: String) {
    companion object {
        fun toDepartment(dto: List<DepartmentDto>): List<Department> =
            dto.map { Department(id = it.departmentId, name = it.displayName) }
    }
}
