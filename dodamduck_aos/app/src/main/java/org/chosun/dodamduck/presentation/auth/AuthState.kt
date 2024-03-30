package org.chosun.dodamduck.presentation.auth

import org.chosun.dodamduck.presentation.base.State

data class AuthState(
    val isRegisterLoading: Boolean = false,
    val registerError: String? = null,
    val registerResult: Boolean = false,
    val isLoginLoading: Boolean? = null,
    val loginError: String? = null,
    val loginResult: Boolean? = null,
): State {
    companion object {
        fun init() = AuthState()
    }
}