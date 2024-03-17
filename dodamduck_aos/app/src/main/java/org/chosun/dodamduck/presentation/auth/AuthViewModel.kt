package org.chosun.dodamduck.presentation.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.chosun.dodamduck.data.dto.auth.AuthDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.remote.auth.RequestLogin
import org.chosun.dodamduck.domain.usecase.remote.auth.RequestRegister
import org.chosun.dodamduck.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val requestLogin: RequestLogin,
    private val requestRegister: RequestRegister
) : BaseViewModel<AuthState, AuthEvent, AuthSideEffect>(
    AuthReducer(AuthState.init())
) {

    fun sendSideEffect(effect: AuthSideEffect) {
        setEffect(effect)
    }

    fun loginRequest(
        userID: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            requestLogin(AuthDto(userID, userPassword)).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.login_success) sendEvent(AuthEvent.onSuccessLogin)
                        else sendEvent(AuthEvent.onFailLogin("error"))
                    }

                    is ApiResult.Error-> sendEvent(AuthEvent.onErrorLogin(error = apiResult.message ?: "error"))

                    is ApiResult.Exception -> { }
                }
            }
        }
    }

    fun registerRequest(
        userID: String,
        userPassword: String
    ) {
        sendEvent(AuthEvent.onRequestRegister(userID, userPassword))
        viewModelScope.launch {
            requestRegister(userID, userPassword).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.error == "false") sendEvent(AuthEvent.onSuccessRegister)
                        else sendEvent(AuthEvent.onFailRegister)
                    }

                    is ApiResult.Error-> sendEvent(AuthEvent.onErrorRegister(error = apiResult.message ?: "error"))

                    is ApiResult.Exception -> { }
                }
            }
        }
    }
}