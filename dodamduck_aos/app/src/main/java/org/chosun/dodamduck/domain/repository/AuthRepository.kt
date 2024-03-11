package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.model.dto.LoginDTO
import org.chosun.dodamduck.model.network.DodamDuckResponse

interface AuthRepository {
    suspend fun requestLogin(
        userID: String,
        userPassword: String
    ): Flow<ApiResult<LoginDTO>>

    suspend fun requestRegister(
        userID: String,
        userPassword: String,
    ): Flow<ApiResult<DodamDuckResponse>>

}