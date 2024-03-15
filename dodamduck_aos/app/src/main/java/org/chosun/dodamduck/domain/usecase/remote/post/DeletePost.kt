package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class DeletePost<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
) {
    operator fun invoke(
        postID: String,
        userID: String
    ): Flow<ApiResult<DodamDuckResponse?>> = channelFlow {
        postRepo.deletePost(postID, userID).collectLatest { apiResult ->
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