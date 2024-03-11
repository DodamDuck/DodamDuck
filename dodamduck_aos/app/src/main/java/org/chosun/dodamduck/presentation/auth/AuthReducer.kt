package org.chosun.dodamduck.presentation.auth

import org.chosun.dodamduck.presentation.base.Reducer

class AuthReducer(state: AuthState): Reducer<AuthState, AuthEvent>(state) {
    override fun reduce(oldState: AuthState, event: AuthEvent) {
        when(event) {
            is AuthEvent.onRequestRegister -> setState(oldState.copy(registerResult = false, isRegisterLoading = true))
            is AuthEvent.onSuccessRegister -> setState(oldState.copy(registerResult = true, isRegisterLoading = false))
            is AuthEvent.onFailRegister -> setState(oldState.copy(registerResult = false, isRegisterLoading = false))
            is AuthEvent.onErrorRegister -> setState(oldState.copy(registerResult = false, registerError = event.error))
        }
    }
}