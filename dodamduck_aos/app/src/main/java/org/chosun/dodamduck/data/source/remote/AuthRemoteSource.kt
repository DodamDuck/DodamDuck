package org.chosun.dodamduck.data.source.remote

import org.chosun.dodamduck.network.service.AuthApiService
import javax.inject.Inject

class AuthRemoteSource @Inject constructor(
    private val authService: AuthApiService
) {

    suspend fun requestLogin(
        userID: String,
        password: String
    ) = authService.requestLogin(userID, password)

    suspend fun requestRegister(
        userID: String,
        password: String
    ) = authService.requestRegister(userID, password)
}
