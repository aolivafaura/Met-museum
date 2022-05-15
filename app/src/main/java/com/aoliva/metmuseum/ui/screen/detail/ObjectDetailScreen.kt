package com.aoliva.metmuseum.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ObjectDetailScreen(
    navController: NavController,
    viewModel: ObjectDetailScreenViewModel = hiltViewModel()
) {
    val metObject by viewModel.state.collectAsState()

    TODO()
}