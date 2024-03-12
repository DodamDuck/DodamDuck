package org.chosun.dodamduck.presentation.library

import org.chosun.dodamduck.presentation.base.Reducer

class ToyLibraryReducer(state: ToyLibraryState): Reducer<ToyLibraryState, ToyLibraryEvent>(state) {
    override fun reduce(oldState: ToyLibraryState, event: ToyLibraryEvent) {
        when(event) {
            is ToyLibraryEvent.OnRequestData -> setState(oldState.copy(isLoading = true))
            is ToyLibraryEvent.OnFetchToyList -> setState(oldState.copy(toyList = event.toyList))
            is ToyLibraryEvent.OnCompleteData -> setState(oldState.copy(isLoading = false, isError = false))
            is ToyLibraryEvent.OnError -> setState(oldState.copy(isLoading = false, isError = true))
        }
    }
}