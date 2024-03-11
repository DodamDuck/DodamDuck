package org.chosun.dodamduck.presentation.auth

import org.chosun.dodamduck.presentation.base.State

data class AuthState(
    val isRegisterLoading: Boolean = false,
    val registerError: String? = null,
    val registerResult: Boolean = false,
    val isLoginLoading: Boolean = false,
    val loginError: String? = null,
    val loginResult: Boolean = false,
): State {
    companion object {
        fun init() = AuthState()
    }
}