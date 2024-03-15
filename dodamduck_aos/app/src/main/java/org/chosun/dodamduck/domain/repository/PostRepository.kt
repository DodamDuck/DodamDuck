package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.domain.model.ApiResult

interface PostRepository<ALL>: BasePostRepository<ALL> {
    suspend fun fetchList(categoryID: String): Flow<ApiResult<List<PostDTO>>>

    suspend fun fetchCategories(): Flow<ApiResult<List<CategoryDTO>>>
}