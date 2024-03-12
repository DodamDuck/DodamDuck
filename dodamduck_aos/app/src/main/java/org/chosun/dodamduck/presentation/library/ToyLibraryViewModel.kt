package org.chosun.dodamduck.presentation.library

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.remote.toylibrary.GetToys
import org.chosun.dodamduck.presentation.base.BaseViewModel
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
        sendEvent(ToyLibraryEvent.OnRequestData)
        viewModelScope.launch {
            getToys().collectLatest { apiResult ->
                when(apiResult) {
                    is ApiResult.Success -> {
                       sendEvent(ToyLibraryEvent.OnFetchToyList(apiResult.value))
                    }

                    is ApiResult.Error-> sendEvent(ToyLibraryEvent.OnError)

                    is ApiResult.Exception -> { }
                }
            }
            sendEvent(ToyLibraryEvent.OnCompleteData)
        }
    }
}