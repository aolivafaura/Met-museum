package com.aoliva.metmuseum.ui.screen.departments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.ui.navigation.Destinations
import com.aoliva.metmuseum.ui.screen.departments.model.DepartmentUi
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenViewAction
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenViewEffect
import com.aoliva.metmuseum.ui.theme.MetBlackJet
import com.aoliva.metmuseum.ui.theme.MetWhiteCultured

@Composable
fun DepartmentsScreen(
    navController: NavController,
    viewModel: DepartmentsScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.viewEffect.collect { effect ->
            when (effect) {
                is DepartmentsScreenViewEffect.NavigateToDepartment -> {
                    navController.navigate(Destinations.DepartmentObjectsList.createRoute(effect.departmentId))
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
                DepartmentsList(screenState.data) { id ->
                    viewModel.processAction(DepartmentsScreenViewAction.OnDepartmentsClick(id))
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

@Composable
private fun DepartmentsList(list: List<DepartmentUi>, onItemClick: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.background(MetBlackJet)) {
        itemsIndexed(list) { index, item ->
            DepartmentsListItem(
                name = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item.id) })
            if (index < list.lastIndex) {
                Divider(color = MetWhiteCultured, startIndent = 8.dp)
            }
        }
    }
}

@Composable
private fun DepartmentsListItem(name: String, modifier: Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = name,
            modifier = Modifier.padding(8.dp),
            color = MetWhiteCultured
        )
    }
}

@Preview(widthDp = 300)
@Composable
private fun DepartmentsListPreview() {
    DepartmentsList(
        listOf(
            DepartmentUi(1, "Department 1"),
            DepartmentUi(2, "Department 2")
        )
    ) {}
}
