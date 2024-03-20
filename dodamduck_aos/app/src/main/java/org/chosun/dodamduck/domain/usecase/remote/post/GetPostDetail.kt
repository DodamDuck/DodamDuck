package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.network.response.PostDetailResponse
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class GetPostDetail<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<String, PostDetailResponse>() {

    override suspend fun execute(params: String): Flow<ApiResult<PostDetailResponse>> {
        return postRepo.fetchDetail(params)
    }
}