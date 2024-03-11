package org.chosun.dodamduck.presentation.auth

import org.chosun.dodamduck.presentation.base.Event

sealed class AuthEvent: Event {
    data class onRequestRegister(val userId: String, val userPassword: String) : AuthEvent()

    data class onErrorRegister(val error: String) : AuthEvent()

    object onSuccessRegister : AuthEvent()

    object onFailRegister : AuthEvent()

    data class onRequestLogin(val userId: String, val userPassword: String) : AuthEvent()

    data class onErrorLogin(val error: String) : AuthEvent()

    object onSuccessLogin : AuthEvent()

    object onFailLogin : AuthEvent()
}