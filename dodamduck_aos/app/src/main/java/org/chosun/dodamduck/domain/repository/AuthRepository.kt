package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.data.dto.auth.LoginDto
import org.chosun.dodamduck.network.response.DodamDuckResponse

interface AuthRepository {
    suspend fun requestLogin(
        userID: String,
        userPassword: String
    ): Flow<ApiResult<LoginDto>>

    suspend fun requestRegister(
        userID: String,
        userPassword: String,
    ): Flow<ApiResult<DodamDuckResponse>>

}