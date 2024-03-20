package org.chosun.dodamduck.domain.usecase.remote.toylibrary

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ToyLibraryRepository
import org.chosun.dodamduck.data.dto.library.ToyLibraryDto
import javax.inject.Inject

class GetToys @Inject constructor(
    private val toyLibraryRepo: ToyLibraryRepository
) {
    operator fun invoke(): Flow<ApiResult<List<ToyLibraryDto>>> = channelFlow {
        toyLibraryRepo.fetchToyList().collectLatest { apiResult ->
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