package org.chosun.dodamduck.presentation.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

    private val _loginState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    private val _loginUiState: MutableStateFlow<LoginUiState> =
        MutableStateFlow(LoginUiState.LOADING)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    suspend fun loginRequest(
        userID: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            requestLogin(userID, userPassword).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        _loginState.value = apiResult.value.login_success
                        _loginUiState.value =
                            if (loginState.value) LoginUiState.SUCCESS else LoginUiState.FAIL
                    }

                    else -> {
                        _loginUiState.value = LoginUiState.ERROR
                    }
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

enum class LoginUiState {
    LOADING,
    SUCCESS,
    FAIL,
    ERROR
}