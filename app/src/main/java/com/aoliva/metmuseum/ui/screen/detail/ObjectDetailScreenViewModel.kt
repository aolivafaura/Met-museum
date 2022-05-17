package com.aoliva.metmuseum.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.common.model.LoadingErrorSuccess
import com.aoliva.metmuseum.common.mvi.BaseMviViewModel
import com.aoliva.metmuseum.data.repository.MetRepository
import com.aoliva.metmuseum.domain.model.MetObject
import com.aoliva.metmuseum.ui.navigation.MET_OBJECT_ID
import com.aoliva.metmuseum.ui.screen.detail.mapper.FromMetObjectToMetObjectUi
import com.aoliva.metmuseum.ui.screen.detail.mvi.ObjectDetailScreenPartialState
import com.aoliva.metmuseum.ui.screen.detail.mvi.ObjectDetailScreenState
import com.aoliva.metmuseum.ui.screen.detail.mvi.ObjectDetailScreenViewAction
import com.aoliva.metmuseum.ui.screen.detail.mvi.ObjectDetailScreenViewEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjectDetailScreenViewModel @Inject constructor(
    override val dispatchers: DispatcherProvider,
    override val savedStateHandle: SavedStateHandle,
    private val repository: MetRepository,
    private val fromMetObjectToMetObjectUi: FromMetObjectToMetObjectUi
) : BaseMviViewModel<ObjectDetailScreenState, ObjectDetailScreenPartialState, ObjectDetailScreenViewAction, ObjectDetailScreenViewEffect>(
    ObjectDetailScreenState.INITIAL
) {

    init {
        getObjects()
    }

    private fun getObjects() {
        viewModelScope.launch(dispatchers.io) {
            savedStateHandle.get<String>(MET_OBJECT_ID)?.let { id ->
                repository.getObject(id.toInt())
                    .collect { metObject ->
                        reduceAndEmitNewState(
                            oldViewState = state.value,
                            partialState = ObjectDetailScreenPartialState.Success(metObject.mapToUi())
                        )
                    }
            } ?: run {
                TODO("Define the error case")
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

    override fun processAction(action: ObjectDetailScreenViewAction) {
        // No actions received from view
    }

    override fun processViewEffect(viewEffect: ObjectDetailScreenViewEffect) {
        // No actions view effects to notify
    }

}

