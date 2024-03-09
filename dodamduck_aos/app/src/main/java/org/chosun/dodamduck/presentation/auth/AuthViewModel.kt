package org.chosun.dodamduck.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.remote.auth.RequestLogin
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val requestLogin: RequestLogin
) : ViewModel() {

    private val _loginState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    private val _loginUiState: MutableStateFlow<LoginUiState> =
        MutableStateFlow(LoginUiState.LOADING)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    private val _isRegisterState = MutableStateFlow("true")
    val isRegisterState: StateFlow<String> = _isRegisterState
    suspend fun loginRequest(
        userID: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            requestLogin(userID, userPassword).collectLatest { apiResult ->
                when(apiResult) {
                    is ApiResult.Success -> {
                        _loginState.value = apiResult.value.login_success
                        _loginUiState.value = if(loginState.value) LoginUiState.SUCCESS else LoginUiState.FAIL
                    }
                    else -> {
                        _loginUiState.value = LoginUiState.ERROR
                    }
                }
            }
        }
    }

    suspend fun registerRequest(
        userID: String,
        userPassword: String
    ) {
        viewModelScope.launch {
//            val result = repository.requestRegister(userID, userPassword)
//            _isRegisterState.value = result?.error ?: "true"
        }
    }
}

enum class LoginUiState {
    LOADING,
    SUCCESS,
    FAIL,
    ERROR
}