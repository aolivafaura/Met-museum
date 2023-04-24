package com.aoliva.metmuseum.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess

@Composable
fun ObjectDetailScreen(
    navController: NavController,
    viewModel: ObjectDetailScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box {
        when (val metObject = state.dataState) {
            is LoadingErrorSuccess.Loading -> {
                Text("loading")
            }
            is LoadingErrorSuccess.Error -> {
                Text("error")
            }
            is LoadingErrorSuccess.Success -> {
                with(metObject.data) {
                    ObjectDetail(title, author, image)
                }
            }
        }
    }
}

@Composable
private fun ObjectDetail(title: String, author: String?, image: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(model = image, contentDescription = "")
        Text(title)
        Text(author?.takeIf { it.isNotBlank() } ?: "Unknown")
    }
}