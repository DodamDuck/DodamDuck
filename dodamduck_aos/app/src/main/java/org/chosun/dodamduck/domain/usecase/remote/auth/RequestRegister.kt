package org.chosun.dodamduck.domain.usecase.remote.auth

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.auth.AuthUseCaseDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.response.DodamDuckResponse
import javax.inject.Inject

class RequestRegister @Inject constructor(
    private val authRepo: AuthRepository
): BaseResultUseCase<AuthUseCaseDto, DodamDuckResponse>() {

    override suspend fun execute(authUseCaseDto: AuthUseCaseDto): Flow<ApiResult<DodamDuckResponse>> {
        return authRepo.requestRegister(authUseCaseDto.userID, authUseCaseDto.password)
    }
}