package org.chosun.dodamduck.domain.usecase.remote.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.model.dto.LoginDTO
import javax.inject.Inject

class RequestLogin @Inject constructor(
    private val authRepo: AuthRepository
) {
    operator fun invoke(
        userID: String,
        password: String
    ): Flow<ApiResult<LoginDTO>> = channelFlow {
        authRepo.requestLogin(userID, password).collectLatest { apiResult ->
            if(apiResult is ApiResult.Success) {
                send(ApiResult.Success(apiResult.value))
            } else {
                if (apiResult is ApiResult.Error) {
                    send(apiResult)
                } else if (apiResult is ApiResult.Exception) {
                    send(apiResult)
                }
            }
        }
    }
}