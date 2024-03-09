package org.chosun.dodamduck.domain.repository

import org.chosun.dodamduck.model.dto.LoginDTO
import org.chosun.dodamduck.model.network.DodamDuckResponse

interface AuthRepository {
    suspend fun requestLogin(
        userID: String,
        userPassword: String
    ): LoginDTO?

    suspend fun requestRegister(
        userID: String,
        userPassword: String,
    ): DodamDuckResponse?

}