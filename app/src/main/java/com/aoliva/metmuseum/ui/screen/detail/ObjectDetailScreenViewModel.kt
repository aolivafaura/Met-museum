package com.aoliva.metmuseum.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.mvi.ViewStateReducer
import com.aoliva.metmuseum.common.viewmodel.BaseViewModel
import com.aoliva.metmuseum.data.repository.MetRepository
import com.aoliva.metmuseum.domain.model.MetObject
import com.aoliva.metmuseum.ui.navigation.DEPARTMENT_OBJECTS_ID
import com.aoliva.metmuseum.ui.screen.detail.mapper.FromMetObjectToMetObjectUi
import com.aoliva.metmuseum.ui.screen.detail.mvi.ObjectDetailScreenPartialState
import com.aoliva.metmuseum.ui.screen.detail.mvi.ObjectDetailScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjectDetailScreenViewModel @Inject constructor(
    override val dispatchers: DispatcherProvider,
    override val savedStateHandle: SavedStateHandle,
    private val repository: MetRepository,
    private val fromMetObjectToMetObjectUi: FromMetObjectToMetObjectUi
) : BaseViewModel(),
    ViewStateReducer<ObjectDetailScreenState, ObjectDetailScreenPartialState> {

    private val _state = MutableStateFlow(ObjectDetailScreenState.INITIAL)
    val state: StateFlow<ObjectDetailScreenState> = _state

    init {
        viewModelScope.launch(dispatchers.io) {
            val id = savedStateHandle.get<String>(DEPARTMENT_OBJECTS_ID)
            id?.let {
                repository.getObject(id.toInt())
                    .collect { metObject ->
                        reduceViewState(
                            oldViewState = _state.value,
                            partialState = ObjectDetailScreenPartialState.Success(metObject.mapToUi())
                        )
                    }
            }
        }
    }

    private fun MetObject.mapToUi() = fromMetObjectToMetObjectUi.map(this)

    override fun reduceViewState(
        oldViewState: ObjectDetailScreenState,
        partialState: ObjectDetailScreenPartialState
    ): ObjectDetailScreenState =
        when (partialState) {
            is ObjectDetailScreenPartialState.Success -> {
                oldViewState.copy(dataState = LoadingErrorSuccess.Success(partialState.data))
            }
            else -> {
                throw RuntimeException("Invalid partial state received: $partialState")
            }
        }

}

