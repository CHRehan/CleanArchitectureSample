package com.clean.architecture.features.countries.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * Created by Rehan Sarwar on 07/06/2022.
 */
interface ViewState
abstract class BaseViewModel<VIEW_STATE : ViewState> : ViewModel() {
    abstract fun initialState(): VIEW_STATE

    private val _viewState = mutableStateOf(initialState())
    val viewState: State<VIEW_STATE> = _viewState

    fun updateState(updatedState: (lastState: VIEW_STATE) -> VIEW_STATE) {
        _viewState.value = updatedState(currentViewState())
    }

    fun currentViewState() = _viewState.value
}
