package org.chosun.dodamduck.presentation.auth

import org.chosun.dodamduck.presentation.base.Event

sealed class AuthEvent: Event {
    object OnSuccessRegister : AuthEvent()

    data class OnErrorRegister(val error: String) : AuthEvent()

    object OnLoadingRegister : AuthEvent()

    object OnSuccessLogin : AuthEvent()

    data class OnErrorLogin(val error: String) : AuthEvent()

    object OnLoadingLogin : AuthEvent()
}