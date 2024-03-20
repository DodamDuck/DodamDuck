package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class SearchPost<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<String, List<T>>() {

    override suspend fun execute(params: String): Flow<ApiResult<List<T>>> {
        return postRepo.searchPost(params)
    }
}