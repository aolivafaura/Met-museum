package com.aoliva.metmuseum.ui.screen.department

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.mvi.ViewActionProcessor
import com.aoliva.metmuseum.common.mvi.ViewEffectEmitter
import com.aoliva.metmuseum.common.mvi.ViewStateReducer
import com.aoliva.metmuseum.common.viewmodel.BaseViewModel
import com.aoliva.metmuseum.data.repository.MetRepository
import com.aoliva.metmuseum.domain.model.MetObject
import com.aoliva.metmuseum.ui.navigation.DEPARTMENT_ID
import com.aoliva.metmuseum.ui.screen.department.mapper.FromMetObjectToMetObjectListItemUi
import com.aoliva.metmuseum.ui.screen.department.mvi.DepartmentScreenPartialState
import com.aoliva.metmuseum.ui.screen.department.mvi.DepartmentScreenState
import com.aoliva.metmuseum.ui.screen.department.mvi.DepartmentScreenViewAction
import com.aoliva.metmuseum.ui.screen.department.mvi.DepartmentScreenViewEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentScreenViewModel @Inject constructor(
    override val dispatchers: DispatcherProvider,
    override val savedStateHandle: SavedStateHandle,
    private val repository: MetRepository,
    private val fromMetObjectToMetObjectListItemUi: FromMetObjectToMetObjectListItemUi
) : BaseViewModel(),
    ViewStateReducer<DepartmentScreenState, DepartmentScreenPartialState>,
    ViewActionProcessor<DepartmentScreenViewAction>,
    ViewEffectEmitter<DepartmentScreenViewEffect> {

    private val _state = MutableStateFlow(DepartmentScreenState.INITIAL)
    val state: StateFlow<DepartmentScreenState> = _state

    private val _viewEffect = MutableSharedFlow<DepartmentScreenViewEffect>()
    val viewEffect: SharedFlow<DepartmentScreenViewEffect> = _viewEffect

    init {
        viewModelScope.launch(dispatchers.io) {
            val departmentId = savedStateHandle.get<String>(DEPARTMENT_ID)
            departmentId?.let {
                repository.getDepartmentObjects(departmentId.toInt())
                    // Limit for now. I don't want to collapse Met museum servers
                    .map { ids -> ids.subList(0, 10) }
                    .map { ids ->
                        val objectsInfo = mutableListOf<Deferred<MetObject>>()
                        for (id in ids) {
                            val objectInfo = async {
                                repository.getObject(id).first()
                            }
                            objectsInfo.add(objectInfo)
                        }
                        objectsInfo.awaitAll()
                    }
                    .collect { metObjects ->
                        _state.emit(
                            reduceViewState(
                                oldViewState = _state.value,
                                partialState = DepartmentScreenPartialState.Success(metObjects.mapToUi())
                            )
                        )
                    }
            }
        }
    }

    private fun List<MetObject>.mapToUi() = fromMetObjectToMetObjectListItemUi.map(this)

    override fun reduceViewState(
        oldViewState: DepartmentScreenState,
        partialState: DepartmentScreenPartialState
    ): DepartmentScreenState =
        when (partialState) {
            is DepartmentScreenPartialState.Success -> {
                oldViewState.copy(dataState = LoadingErrorSuccess.Success(partialState.data))
            }
            else -> {
                throw RuntimeException("Invalid partial state received: $partialState")
            }
        }

    override fun processAction(action: DepartmentScreenViewAction) {
        when (action) {
            is DepartmentScreenViewAction.OnItemClick -> {
                emitViewEffect(DepartmentScreenViewEffect.NavigateToObjectDetail(action.id))
            }
        }
    }

    override fun emitViewEffect(viewEffect: DepartmentScreenViewEffect) {
        when (viewEffect) {
            is DepartmentScreenViewEffect.NavigateToObjectDetail -> {
                viewModelScope.launch {
                    _viewEffect.emit(viewEffect)
                }
            }
        }
    }
}