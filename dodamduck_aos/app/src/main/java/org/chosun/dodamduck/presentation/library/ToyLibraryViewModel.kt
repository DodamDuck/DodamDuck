package org.chosun.dodamduck.presentation.library

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.chosun.dodamduck.domain.usecase.remote.toylibrary.GetToys
import org.chosun.dodamduck.presentation.base.BaseViewModel
import org.chosun.dodamduck.presentation.processApiResult
import javax.inject.Inject

@HiltViewModel
class ToyLibraryViewModel @Inject constructor(
    private val getToys: GetToys
):  BaseViewModel<ToyLibraryState, ToyLibraryEvent, ToyLibrarySideEffect>(
    ToyLibraryReducer(ToyLibraryState.init())
) {

    fun sendSideEffect(effect: ToyLibrarySideEffect) {
        setEffect(effect)
    }

    fun getToyInfos() {
        viewModelScope.processApiResult(getToys(null)) {
            onLoading { sendEvent(ToyLibraryEvent.OnLoading) }
            onSuccess { data -> sendEvent(ToyLibraryEvent.OnSuccessToyList(data)) }
            onError { sendEvent(ToyLibraryEvent.OnError) }
        }
    }
}