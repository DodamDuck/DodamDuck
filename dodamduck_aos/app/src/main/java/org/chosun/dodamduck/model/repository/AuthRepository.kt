package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.LoginDTO
import org.chosun.dodamduck.model.network.AuthApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val service: AuthApiService?
) {
    suspend fun requestLogin(
        userID: String,
        userPassword: String
    ): LoginDTO? {
        return service?.requestLogin(userID, userPassword)
    }
}