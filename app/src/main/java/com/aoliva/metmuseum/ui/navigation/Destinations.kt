package com.aoliva.metmuseum.ui.navigation

const val DEPARTMENT_ID = "id"
const val MET_OBJECT_ID = "id"

sealed class Destinations(val route: String) {

    object DepartmentsScreen : Destinations("departmentsScreen")

    object DepartmentObjectsList : Destinations("departmentsObjectsList/{$DEPARTMENT_ID}") {
        fun createRoute(id: Int) = "departmentsObjectsList/$id"
    }

    object MetObjectDetail : Destinations("metObjectDetail/{$MET_OBJECT_ID}") {
        fun createRoute(id: Int) = "metObjectDetail/$id"
    }
}











