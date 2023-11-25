package org.chosun.dodamduck.model.network

import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.dto.PostDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApiService {

    @GET("ContentShare.php")
    suspend fun getPostList(): List<PostDTO>?

    @GET("ContentShare_Detail.php")
    suspend fun getPostDetail(
        @Query("share_id") shareID: String
    ): PostDetailResponse?
}