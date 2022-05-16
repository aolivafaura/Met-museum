package com.aoliva.metmuseum.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aoliva.metmuseum.ui.navigation.DEPARTMENT_ID
import com.aoliva.metmuseum.ui.navigation.Destinations
import com.aoliva.metmuseum.ui.screen.department.DepartmentScreen
import com.aoliva.metmuseum.ui.screen.departments.DepartmentsScreen
import com.aoliva.metmuseum.ui.screen.detail.ObjectDetailScreen
import com.aoliva.metmuseum.ui.theme.MetMuseumTheme

@Composable
fun MetApp() {
    MetMuseumTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Destinations.DepartmentsScreen.route
        ) {
            composable(route = Destinations.DepartmentsScreen.route) {
                DepartmentsScreen(navController = navController)
            }
            composable(
                route = Destinations.DepartmentObjectsList.route,
                arguments = listOf(navArgument(DEPARTMENT_ID) { NavType.IntType })
            ) {
                DepartmentScreen(navController = navController)
            }
            composable(
                route = Destinations.MetObjectDetail.route,
                arguments = listOf(navArgument(DEPARTMENT_ID) { NavType.IntType })
            ) {
                ObjectDetailScreen(navController = navController)
            }
        }
    }
}