package org.chosun.dodamduck.data.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.data.dto.LoginDTO
import org.chosun.dodamduck.network.DodamDuckResponse
import org.chosun.dodamduck.data.safeFlow
import org.chosun.dodamduck.data.source.remote.AuthRemoteSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteSource
): AuthRepository {
    override suspend fun requestLogin(
        userID: String,
        userPassword: String
    ): Flow<ApiResult<LoginDTO>> = safeFlow {
        authRemoteSource.requestLogin(userID, userPassword)
    }

    override suspend fun requestRegister(
        userID: String,
        userPassword: String,
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        authRemoteSource.requestRegister(userID, userPassword)
    }
}