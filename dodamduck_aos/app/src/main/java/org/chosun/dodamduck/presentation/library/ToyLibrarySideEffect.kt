package org.chosun.dodamduck.presentation.library

import org.chosun.dodamduck.presentation.base.SideEffect

sealed class ToyLibrarySideEffect: SideEffect {

    data class Toast(val text: String): ToyLibrarySideEffect()
}