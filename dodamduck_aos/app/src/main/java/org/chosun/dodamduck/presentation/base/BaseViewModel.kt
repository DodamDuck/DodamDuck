package org.chosun.dodamduck.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State, E : Event, A : SideEffect>(
    private val reducer: Reducer<S, E>
): ViewModel() {

    val uiState = reducer.uiState

    private val _effect = MutableSharedFlow<A>()
    val effect = _effect.asSharedFlow()

    protected fun sendEvent(event: E) {
        reducer.sendEvent(event)
    }

    protected fun setEffect(vararg builder: A) {
        for (effectValue in builder) {
            viewModelScope.launch { _effect.emit(effectValue) }
        }
    }
}