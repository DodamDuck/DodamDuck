package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.PostRepository
import javax.inject.Inject

class UploadPost<T> @Inject constructor(
    private val postRepo: PostRepository<T>
) {
    operator fun invoke(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ): Flow<ApiResult<Boolean>> = channelFlow {
        postRepo.uploadPost(userId, categoryId, title, content, location, image)
            .collectLatest { apiResult ->
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