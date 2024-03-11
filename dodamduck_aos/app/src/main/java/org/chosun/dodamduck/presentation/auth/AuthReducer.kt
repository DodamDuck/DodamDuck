package org.chosun.dodamduck.presentation.auth

import org.chosun.dodamduck.presentation.base.Reducer

class AuthReducer(state: AuthState): Reducer<AuthState, AuthEvent>(state) {
    override fun reduce(oldState: AuthState, event: AuthEvent) {
        when(event) {
            is AuthEvent.onRequestRegister -> setState(oldState.copy(registerResult = false, isRegisterLoading = true))
            is AuthEvent.onSuccessRegister -> setState(oldState.copy(registerResult = true, isRegisterLoading = false))
            is AuthEvent.onFailRegister -> setState(oldState.copy(registerResult = false, isRegisterLoading = false))
            is AuthEvent.onErrorRegister -> setState(oldState.copy(registerResult = false, registerError = event.error, loginError = null))
            is AuthEvent.onRequestLogin -> setState(oldState.copy(loginError = null, loginResult = false, isLoginLoading = true))
            is AuthEvent.onSuccessLogin -> setState(oldState.copy(loginResult = true, isLoginLoading = false))
            is AuthEvent.onFailLogin -> setState(oldState.copy(loginResult = false, isLoginLoading = false, loginError = event.error))
            is AuthEvent.onErrorLogin -> setState(oldState.copy(loginResult = false, loginError = event.error))
        }
    }
}