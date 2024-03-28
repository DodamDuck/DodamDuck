package org.chosun.dodamduck.presentation.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.chosun.dodamduck.data.dto.auth.AuthUseCaseDto
import org.chosun.dodamduck.data.model.DodamDuckData
import org.chosun.dodamduck.domain.usecase.remote.auth.RequestLogin
import org.chosun.dodamduck.domain.usecase.remote.auth.RequestRegister
import org.chosun.dodamduck.presentation.base.BaseViewModel
import org.chosun.dodamduck.presentation.processApiResult
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
        userPassword: String,
        loginCheckSkip: Boolean = false
    ) {
        viewModelScope.processApiResult(requestLogin(AuthUseCaseDto(userID, userPassword))) {
            onLoading {
                if (loginCheckSkip)
                    sendEvent(AuthEvent.OnLoadingLogin)
            }
            onSuccess { data ->
                if (data.loginSuccess) {

                    if(loginCheckSkip) {
                        delay(2000)
                        sendEvent(AuthEvent.OnSuccessLogin)
                    } else {
                        sendEvent(AuthEvent.OnSuccessLogin)
                    }

                    DodamDuckData.userInfo = data
                    sendSideEffect(AuthSideEffect.NavigateToHomeScreen)
                } else {
                    sendSideEffect(AuthSideEffect.Toast("로그인 정보를 다시 확인해 주세요."))
                }
            }
            onError { error -> AuthEvent.OnErrorLogin(error = error.message ?: "error") }
        }
    }

    fun registerRequest(
        userID: String,
        userPassword: String
    ) {
        viewModelScope.processApiResult(requestRegister(AuthUseCaseDto(userID, userPassword))) {
            onLoading { sendEvent(AuthEvent.OnLoadingRegister) }
            onSuccess { data ->
                if (data.error == "false") {
                    sendEvent(AuthEvent.OnSuccessRegister)
                    sendSideEffect(AuthSideEffect.NavigateToLoginScreen)
                } else {
                    sendSideEffect(AuthSideEffect.Toast("비밀번호가 서로 일치하지 않습니다."))
                }
            }
            onError { error ->
                sendEvent(
                    AuthEvent.OnErrorRegister(
                        error = error.message ?: "error"
                    )
                )
            }
        }
    }
}