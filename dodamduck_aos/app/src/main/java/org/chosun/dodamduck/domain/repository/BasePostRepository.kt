package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.network.DodamDuckResponse

interface BasePostRepository<ALL> {
    suspend fun fetchList(): Flow<ApiResult<List<ALL>>>

    suspend fun fetchDetail(id: String): Flow<ApiResult<PostDetailResponse?>>

    suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ): Flow<ApiResult<DodamDuckResponse?>>

    suspend fun uploadViewCount(
        postID: String
    ): Flow<ApiResult<DodamDuckResponse>>

    suspend fun deletePost(
        postID: String,
        userID: String
    ): Flow<ApiResult<DodamDuckResponse?>>

    suspend fun createChat(
        postID: String,
        userID: String
    ): Flow<ApiResult<DodamDuckResponse>>

    suspend fun searchPost(query: String): Flow<ApiResult<List<ALL>>>
}