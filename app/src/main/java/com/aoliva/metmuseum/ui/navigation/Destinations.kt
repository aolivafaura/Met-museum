package com.aoliva.metmuseum.ui.navigation

const val DEPARTMENT_OBJECTS_ID = "id"

sealed class Destinations(val route: String) {
    object DepartmentsScreen : Destinations("departmentsScreen")
    object DepartmentObjectsList : Destinations("departmentsObjectsList/{$DEPARTMENT_OBJECTS_ID}") {
        fun createRoute(id: Int) = "departmentsObjectsList/$id"
    }
}