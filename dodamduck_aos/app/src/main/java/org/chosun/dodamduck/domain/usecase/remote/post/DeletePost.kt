package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class DeletePost<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<Pair<String, String>, DodamDuckResponse>() {

    override suspend fun execute(params: Pair<String, String>): Flow<ApiResult<DodamDuckResponse>> {
        return postRepo.deletePost(params.first, params.second)
    }
}