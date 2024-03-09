package org.chosun.dodamduck.presentation.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class Reducer<S: State, E: Event>(initialState: S) {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    fun sendEvent(event: E) {
        reduce(_uiState.value, event)
    }

    fun setState(newState: S) {
        _uiState.value = newState
    }

    abstract fun reduce(oldState: S, event: E)
}