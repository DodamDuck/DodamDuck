package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class UploadViewCount<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<String, DodamDuckResponse>() {
    override suspend fun execute(params: String): Flow<ApiResult<DodamDuckResponse>> {
        return postRepo.uploadViewCount(params)
    }
}