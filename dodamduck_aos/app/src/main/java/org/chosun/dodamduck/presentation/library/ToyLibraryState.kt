package org.chosun.dodamduck.presentation.library

import org.chosun.dodamduck.data.dto.ToyInfo
import org.chosun.dodamduck.presentation.base.State

data class ToyLibraryState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val toyList: List<ToyInfo>? = null
) : State {
    companion object {
        fun init() = ToyLibraryState()
    }
}