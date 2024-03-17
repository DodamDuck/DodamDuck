package org.chosun.dodamduck.domain.usecase.remote.auth

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.data.dto.LoginDTO
import org.chosun.dodamduck.data.dto.auth.AuthDto
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class RequestLogin @Inject constructor(
    private val authRepo: AuthRepository
): BaseResultUseCase<AuthDto, LoginDTO>() {
    override suspend fun execute(authDto: AuthDto): Flow<ApiResult<LoginDTO>> {
        return authRepo.requestLogin(authDto.userID, authDto.password)
    }
}