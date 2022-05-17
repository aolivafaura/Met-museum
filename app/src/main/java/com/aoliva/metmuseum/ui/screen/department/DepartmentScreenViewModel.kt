package com.aoliva.metmuseum.ui.screen.department

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.mvi.BaseMviViewModel
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentScreenViewModel @Inject constructor(
    override val dispatchers: DispatcherProvider,
    override val savedStateHandle: SavedStateHandle,
    private val repository: MetRepository,
    private val fromMetObjectToMetObjectListItemUi: FromMetObjectToMetObjectListItemUi
) : BaseMviViewModel<DepartmentScreenState, DepartmentScreenPartialState, DepartmentScreenViewAction, DepartmentScreenViewEffect>(
    DepartmentScreenState.INITIAL
) {

    init {
        getDepartmentObjects()
    }

    private fun getDepartmentObjects() {
        viewModelScope.launch(dispatchers.io) {
            savedStateHandle.get<String>(DEPARTMENT_ID)?.let { departmentId ->
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
                        reduceAndEmitNewState(
                            oldViewState = state.value,
                            partialState = DepartmentScreenPartialState.Success(metObjects.mapToUi())
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
                processViewEffect(DepartmentScreenViewEffect.NavigateToObjectDetail(action.id))
            }
        }
    }

    override fun processViewEffect(viewEffect: DepartmentScreenViewEffect) {
        when (viewEffect) {
            is DepartmentScreenViewEffect.NavigateToObjectDetail -> {
                emitViewEffect(viewEffect)
            }
        }
    }
}