package org.chosun.dodamduck.presentation.auth

import org.chosun.dodamduck.presentation.base.SideEffect

sealed class AuthSideEffect: SideEffect {

    object NavigateToLoginScreen: AuthSideEffect()

    object NavigateToHomeScreen: AuthSideEffect()

    object NavigateToRegisterScreen: AuthSideEffect()

    data class Toast(val text: String): AuthSideEffect()

}