package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.PostRepository
import javax.inject.Inject

class GetPostCategories<T> @Inject constructor(
    private val postRepo: PostRepository<T>
) {
    operator fun invoke(): Flow<ApiResult<List<CategoryDTO>>> = channelFlow {
        postRepo.fetchCategories().collectLatest { apiResult ->
            if (apiResult is ApiResult.Success) {
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