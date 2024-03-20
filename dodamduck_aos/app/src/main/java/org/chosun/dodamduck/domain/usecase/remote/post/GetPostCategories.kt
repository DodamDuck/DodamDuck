package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.PostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class GetPostCategories<T> @Inject constructor(
    private val postRepo: PostRepository<T>
): BaseResultUseCase<Nothing?, List<CategoryDto>>() {

    override suspend fun execute(params: Nothing?): Flow<ApiResult<List<CategoryDto>>> {
        return postRepo.fetchCategories()
    }
}