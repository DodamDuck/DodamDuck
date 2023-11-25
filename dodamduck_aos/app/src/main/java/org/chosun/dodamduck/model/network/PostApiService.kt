package org.chosun.dodamduck.model.network

import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.dto.PostDetailResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostApiService {

    @GET("ContentShare.php")
    suspend fun getPostList(): List<PostDTO>?

    @GET("ContentShare_Detail.php")
    suspend fun getPostDetail(
        @Query("share_id") shareID: String
    ): PostDetailResponse?

    @POST("upload_share_comment.php")
    @FormUrlEncoded
    suspend fun uploadComment(
        @Field("share_id") postId: String,
        @Field("user_id") userId: String,
        @Field("comment") content: String
    ): DodamDuckResponse
}