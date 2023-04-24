package com.aoliva.metmuseum.ui.screen.department

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.ui.navigation.Destinations
import com.aoliva.metmuseum.ui.screen.department.model.MetObjectListItemUi
import com.aoliva.metmuseum.ui.screen.department.mvi.DepartmentScreenViewAction
import com.aoliva.metmuseum.ui.screen.department.mvi.DepartmentScreenViewEffect
import com.aoliva.metmuseum.ui.theme.MetBlackJet

@Composable
fun DepartmentScreen(
    navController: NavController,
    viewModel: DepartmentScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.viewEffect.collect { effect ->
            when (effect) {
                is DepartmentScreenViewEffect.NavigateToObjectDetail -> {
                    navController.navigate(Destinations.MetObjectDetail.createRoute(effect.id))
                }
            }
        }
    }

    Box {
        when (val screenState = state.dataState) {
            is LoadingErrorSuccess.Loading -> {
                LoadingView()
            }
            is LoadingErrorSuccess.Success -> {
                MetObjectList(screenState.data) { id ->
                    viewModel.processAction(DepartmentScreenViewAction.OnItemClick(id.toInt()))
                }
            }
            is LoadingErrorSuccess.Error -> {
                EmptyView()
            }
        }
    }
}

@Composable
private fun EmptyView() {
    Column() {
        Text("The emptiness")
    }
}

@Composable
private fun LoadingView() {
    Column() {
        Text("The waiting")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MetObjectList(list: List<MetObjectListItemUi>, onItemClick: (String) -> Unit) {
    LazyVerticalGrid(modifier = Modifier.background(MetBlackJet), columns = GridCells.Fixed(2)) {
        itemsIndexed(list) { index, item ->
            ListItem(
                title = item.title,
                author = item.author,
                image = item.image,
                modifier = Modifier.clickable { onItemClick(item.id) })
        }
    }
}

@Composable
private fun ListItem(
    title: String,
    author: String? = null,
    image: String? = null,
    modifier: Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        image?.let {
            AsyncImage(model = image, contentDescription = "")
        } ?: Text("No image")
        Text(title, maxLines = 2)
        Text(author?.takeIf { it.isNotBlank() } ?: "Unknown", maxLines = 2)
    }
}