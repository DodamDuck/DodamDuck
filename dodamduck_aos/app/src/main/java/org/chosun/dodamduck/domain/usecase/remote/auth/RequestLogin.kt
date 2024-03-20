package org.chosun.dodamduck.domain.usecase.remote.auth

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.data.dto.auth.LoginDto
import org.chosun.dodamduck.data.dto.auth.AuthUseCaseDto
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class RequestLogin @Inject constructor(
    private val authRepo: AuthRepository
): BaseResultUseCase<AuthUseCaseDto, LoginDto>() {
    override suspend fun execute(authUseCaseDto: AuthUseCaseDto): Flow<ApiResult<LoginDto>> {
        return authRepo.requestLogin(authUseCaseDto.userID, authUseCaseDto.password)
    }
}