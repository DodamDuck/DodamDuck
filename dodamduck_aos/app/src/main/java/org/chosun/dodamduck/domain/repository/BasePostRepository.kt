package org.chosun.dodamduck.domain.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.network.DodamDuckResponse

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
}