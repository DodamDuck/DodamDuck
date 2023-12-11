package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.data.DodamDuckData
import org.chosun.dodamduck.model.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isLoginState = MutableStateFlow(false)
    val isLoginState: StateFlow<Boolean> = _isLoginState

    private val _isRegisterState = MutableStateFlow("true")
    val isRegisterState: StateFlow<String> = _isRegisterState
    suspend fun loginRequest(
        userID: String,
        userPassword: String
    ): Boolean {
        val result = viewModelScope.async {
            val response = repository.requestLogin(userID, userPassword)
            _isLoginState.value = response?.login_success ?: false
            DodamDuckData.userInfo = response!!
            return@async response.login_success ?: false
        }
        return result.await()
    }

    suspend fun registerRequest(
        userID: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            val result = repository.requestRegister(userID, userPassword)
            _isRegisterState.value = result?.error ?: "true"
        }
    }
}
