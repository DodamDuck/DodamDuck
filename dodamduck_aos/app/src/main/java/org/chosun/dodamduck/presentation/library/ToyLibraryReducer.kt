package org.chosun.dodamduck.presentation.library

import org.chosun.dodamduck.presentation.base.Reducer

class ToyLibraryReducer(state: ToyLibraryState): Reducer<ToyLibraryState, ToyLibraryEvent>(state) {
    override fun reduce(oldState: ToyLibraryState, event: ToyLibraryEvent) {
        when(event) {
            is ToyLibraryEvent.OnLoading -> setState(oldState.copy(isLoading = true))
            is ToyLibraryEvent.OnSuccessToyList -> setState(oldState.copy(isLoading = false, isError = false, toyList = event.toyList))
            is ToyLibraryEvent.OnError -> setState(oldState.copy(isLoading = false, isError = true))
        }
    }
}