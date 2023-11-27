package org.chosun.dodamduck.model.network

import org.chosun.dodamduck.model.dto.CategoryDTO
import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.dto.PostDetailResponse
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query

interface PostApiService {

    @GET("ContentShare.php")
    suspend fun getPostList(): List<PostDTO>?

    @GET("ContentShare.php")
    suspend fun getPostList(
        @Query("category_id") categoryID: String
    ): List<PostDTO>

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

    @POST("content_share_view_up.php")
    @FormUrlEncoded
    suspend fun uploadViews(
        @Field("share_id") shareId: String
    ): DodamDuckResponse

    @GET("Categories.php")
    suspend fun getCategories(): List<CategoryDTO>

    @HTTP(method = "DELETE", path = "PostDelete.php", hasBody = true)
    @FormUrlEncoded
    suspend fun deletePost(
        @Field("post_id") postId: String,
        @Field("user_id") userId: String
    ): DodamDuckResponse?

}