package org.chosun.dodamduck.domain.usecase.remote.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.data.dto.auth.AuthDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class RequestRegister @Inject constructor(
    private val authRepo: AuthRepository
): BaseResultUseCase<AuthDto, DodamDuckResponse>() {

    override suspend fun execute(authDto: AuthDto): Flow<ApiResult<DodamDuckResponse>> {
        return authRepo.requestRegister(authDto.userID, authDto.password)
    }
}