package com.aoliva.metmuseum.ui.screen.departments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.viewmodel.BaseViewModel
import com.aoliva.metmuseum.common.mvi.ViewActionProcessor
import com.aoliva.metmuseum.common.mvi.ViewEffectEmitter
import com.aoliva.metmuseum.common.mvi.ViewStateReducer
import com.aoliva.metmuseum.data.repository.MetRepository
import com.aoliva.metmuseum.domain.model.Department
import com.aoliva.metmuseum.ui.screen.departments.mapper.FromDepartmentToDepartmentUiMapper
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenViewAction
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenViewEffect
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenPartialState
import com.aoliva.metmuseum.ui.screen.departments.mvi.DepartmentsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentsScreenViewModel @Inject constructor(
    override val dispatchers: DispatcherProvider,
    override val savedStateHandle: SavedStateHandle,
    private val repository: MetRepository,
    private val fromDepartmentToDepartmentUiMapper: FromDepartmentToDepartmentUiMapper
) : BaseViewModel(),
    ViewActionProcessor<DepartmentsScreenViewAction>,
    ViewEffectEmitter<DepartmentsScreenViewEffect>,
    ViewStateReducer<DepartmentsScreenState, DepartmentsScreenPartialState> {

    private val _state = MutableStateFlow(DepartmentsScreenState.INITIAL)
    val state: StateFlow<DepartmentsScreenState> = _state

    private val _viewEffect = MutableSharedFlow<DepartmentsScreenViewEffect>()
    val viewEffect: SharedFlow<DepartmentsScreenViewEffect> = _viewEffect

    init {
        viewModelScope.launch(dispatchers.io) {
            repository.getDepartments()
                .collect { departments ->
                    _state.emit(
                        reduceViewState(
                            oldViewState = _state.value,
                            partialState = DepartmentsScreenPartialState.Success(departments.mapToUi())
                        )
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

    override fun emitViewEffect(viewEffect: DepartmentsScreenViewEffect) {
        when (viewEffect) {
            is DepartmentsScreenViewEffect.NavigateToDepartment -> {
                viewModelScope.launch {
                    _viewEffect.emit(viewEffect)
                }
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