package org.chosun.dodamduck.domain.usecase.remote.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.domain.model.ApiResult

abstract class BaseResultUseCase<P, R> {

    abstract suspend fun execute(params: P): Flow<ApiResult<R>>

    operator fun invoke(params: P): Flow<ApiResult<R>> = channelFlow {
        execute(params).collectLatest { apiResult ->
            when (apiResult) {
                is ApiResult.Success -> send(ApiResult.Success(apiResult.value))

                is ApiResult.Error -> send(apiResult)

                is ApiResult.Exception -> send(apiResult)
            }
        }
    }
}