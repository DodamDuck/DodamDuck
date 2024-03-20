package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.domain.model.ApiResult

interface PostRepository<ALL>: BasePostRepository<ALL> {
    suspend fun fetchList(categoryID: String): Flow<ApiResult<List<PostDto>>>

    suspend fun fetchCategories(): Flow<ApiResult<List<CategoryDto>>>
}