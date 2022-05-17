package com.aoliva.metmuseum.ui.screen.departments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.mvi.BaseMviViewModel
import com.aoliva.metmuseum.data.repository.MetRepository
import com.aoliva.metmuseum.domain.model.Department
import com.aoliva.metmuseum.ui.screen.departments.mapper.FromDepartmentToDepartmentUiMapper
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenPartialState
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenState
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenViewAction
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenViewEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentsScreenViewModel @Inject constructor(
    override val dispatchers: DispatcherProvider,
    override val savedStateHandle: SavedStateHandle,
    private val repository: MetRepository,
    private val fromDepartmentToDepartmentUiMapper: FromDepartmentToDepartmentUiMapper
) : BaseMviViewModel<DepartmentsScreenState, DepartmentsScreenPartialState, DepartmentsScreenViewAction, DepartmentsScreenViewEffect>(
    DepartmentsScreenState.INITIAL
) {

    init {
        getDepartments()
    }

    private fun getDepartments() {
        viewModelScope.launch(dispatchers.io) {
            repository.getDepartments()
                .collect { departments ->
                    reduceAndEmitNewState(
                        state.value,
                        DepartmentsScreenPartialState.Success(departments.mapToUi())
                    )
                }
        }
    }

    private fun List<Department>.mapToUi() =
        fromDepartmentToDepartmentUiMapper.map(this)

    override fun processAction(action: DepartmentsScreenViewAction) {
        when (action) {
            is DepartmentsScreenViewAction.OnDepartmentsClick -> {
                handleOnDepartmentClick(action.id)
            }
        }
    }

    private fun handleOnDepartmentClick(id: Int) {
        emitViewEffect(DepartmentsScreenViewEffect.NavigateToDepartment(id))
    }

    override fun processViewEffect(viewEffect: DepartmentsScreenViewEffect) {
        when (viewEffect) {
            is DepartmentsScreenViewEffect.NavigateToDepartment -> {
                emitViewEffect(viewEffect)
            }
        }
    }

    override fun reduceViewState(
        oldViewState: DepartmentsScreenState,
        partialState: DepartmentsScreenPartialState
    ): DepartmentsScreenState =
        when (partialState) {
            is DepartmentsScreenPartialState.Success -> {
                oldViewState.copy(dataState = LoadingErrorSuccess.Success(partialState.data))
            }
            else -> {
                throw RuntimeException("Invalid partial state received: $partialState")
            }
        }
}