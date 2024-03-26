package org.chosun.dodamduck.data.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.data.dto.auth.LoginDto
import org.chosun.dodamduck.network.response.DodamDuckResponse
import org.chosun.dodamduck.data.safeFlow
import org.chosun.dodamduck.data.source.remote.AuthRemoteSource
import org.chosun.dodamduck.network.auth.TokenManager
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteSource,
    private val tokenManager: TokenManager
): AuthRepository {
    override suspend fun requestLogin(
        userID: String,
        userPassword: String
    ): Flow<ApiResult<LoginDto>> = safeFlow {
        val response = authRemoteSource.requestLogin(userID, userPassword)
        tokenManager.saveAccessToken(response.token)
        tokenManager.saveRefreshToken(response.refreshToken)
        response
    }

    override suspend fun requestRegister(
        userID: String,
        userPassword: String,
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        authRemoteSource.requestRegister(userID, userPassword)
    }
}