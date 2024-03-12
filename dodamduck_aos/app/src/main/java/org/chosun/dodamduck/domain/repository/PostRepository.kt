package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.domain.model.ApiResult

interface PostRepository<ALL>: BasePostRepository<ALL> {
    suspend fun uploadPost(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ): Flow<ApiResult<Boolean>>

    suspend fun fetchList(categoryID: String): Flow<ApiResult<List<PostDTO>>>

    suspend fun fetchCategories(): Flow<ApiResult<List<CategoryDTO>>>
}