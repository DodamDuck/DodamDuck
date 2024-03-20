package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.response.DodamDuckResponse
import javax.inject.Inject

class UploadComment<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<Triple<String, String, String>, DodamDuckResponse>() {

    override suspend fun execute(params: Triple<String, String, String>): Flow<ApiResult<DodamDuckResponse>> {
        return postRepo.uploadComment(params.first, params.second, params.third)
    }
}

