package org.chosun.dodamduck.presentation.library

import org.chosun.dodamduck.data.dto.library.ToyLibraryDto
import org.chosun.dodamduck.presentation.base.Event


sealed class ToyLibraryEvent: Event {

    object OnLoading : ToyLibraryEvent()

    object OnError : ToyLibraryEvent()

    data class OnSuccessToyList(val toyList: List<ToyLibraryDto>?) : ToyLibraryEvent()
}