package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.repository.PostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class GetPostList<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<String, List<PostDto>>() {

    override suspend fun execute(params: String): Flow<ApiResult<List<PostDto>>> {
        return (postRepo as PostRepository<T>).fetchList(params)
    }
    operator fun invoke(): Flow<ApiResult<List<T>>> = channelFlow {
        postRepo.fetchList().collectLatest { apiResult ->
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