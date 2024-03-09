package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.model.dto.LoginDTO
import org.chosun.dodamduck.model.network.AuthApiService
import org.chosun.dodamduck.model.network.DodamDuckResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: AuthApiService?
): AuthRepository {
    override suspend fun requestLogin(
        userID: String,
        userPassword: String
    ): LoginDTO? {
        return service?.requestLogin(userID, userPassword)
    }

    override suspend fun requestRegister(
        userID: String,
        userPassword: String,
    ): DodamDuckResponse? {
        return service?.requestRegister(userID, userPassword)
    }
}