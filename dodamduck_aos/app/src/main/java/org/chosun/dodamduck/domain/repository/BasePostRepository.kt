package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import org.chosun.dodamduck.database.SearchHistory
import org.chosun.dodamduck.network.response.PostDetailResponse
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.network.response.DodamDuckResponse

interface BasePostRepository<ALL> {
    suspend fun fetchList(): Flow<ApiResult<List<ALL>>>

    suspend fun fetchDetail(id: String): Flow<ApiResult<PostDetailResponse>>

    suspend fun uploadPost(
        userId: String,
        categoryId: String,
        title: String,
        content: String,
        location: String,
        image: MultipartBody.Part
    ):  Flow<ApiResult<Boolean>>

    suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ): Flow<ApiResult<DodamDuckResponse>>

    suspend fun uploadViewCount(
        postID: String
    ): Flow<ApiResult<DodamDuckResponse>>

    suspend fun deletePost(
        postID: String,
        userID: String
    ): Flow<ApiResult<DodamDuckResponse>>

    suspend fun createChat(
        postID: String,
        userID: String
    ): Flow<ApiResult<DodamDuckResponse>>

    suspend fun searchPost(query: String): Flow<ApiResult<List<ALL>>>

    suspend fun getSearchHistories(): Flow<ApiResult<List<SearchHistory>>>

    suspend fun insertSearchQuery(query: String)

    suspend fun deleteSearchQuery(query: String)

    suspend fun deleteAllSearchQuery()
}