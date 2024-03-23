package org.chosun.dodamduck.presentation.auth

import org.chosun.dodamduck.presentation.base.Reducer

class AuthReducer(state: AuthState): Reducer<AuthState, AuthEvent>(state) {
    override fun reduce(oldState: AuthState, event: AuthEvent) {
        when(event) {
            is AuthEvent.OnSuccessRegister -> setState(oldState.copy(registerResult = true, isRegisterLoading = false))
            is AuthEvent.OnLoadingRegister -> setState(oldState.copy(registerResult = false, isRegisterLoading = true))
            is AuthEvent.OnErrorRegister -> setState(oldState.copy(isRegisterLoading = false, registerResult = false, registerError = event.error))
            is AuthEvent.OnSuccessLogin -> setState(oldState.copy(loginError = null, loginResult = true, isLoginLoading = false))
            is AuthEvent.OnLoadingLogin -> setState(oldState.copy(loginResult = false, isLoginLoading = true))
            is AuthEvent.OnErrorLogin -> setState(oldState.copy(loginResult = false, loginError = event.error))
        }
    }
}