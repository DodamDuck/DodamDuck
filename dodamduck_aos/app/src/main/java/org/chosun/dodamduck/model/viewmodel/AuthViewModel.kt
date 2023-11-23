package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _isLoginState = MutableStateFlow<Boolean>(false)
    val isLoginState: StateFlow<Boolean> = _isLoginState

    private val _isRegisterState = MutableStateFlow<String>("true")
    val isRegisterState: StateFlow<String> = _isRegisterState
    suspend fun loginRequest(
        userID: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            val result = repository.requestLogin(userID, userPassword)
            _isLoginState.value = result?.login_success ?: false
            DodamDuckData.userInfo = result
        }
    }

    suspend fun registerRequest(
        userID: String,
        userPassword: String,
        location: String =""
    ) {
        viewModelScope.launch {
            val result = repository.requestRegister(userID, userPassword, location)
            _isRegisterState.value = result?.error ?: "true"
        }
    }
}
