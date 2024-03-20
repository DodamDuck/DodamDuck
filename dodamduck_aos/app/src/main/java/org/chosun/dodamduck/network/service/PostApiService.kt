package org.chosun.dodamduck.network.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.network.response.DodamDuckResponse
import org.chosun.dodamduck.network.response.PostDetailResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PostApiService {

    @GET("ContentShare.php")
    suspend fun getPostList(): List<PostDto>?

    @GET("ContentShare.php")
    suspend fun getPostList(
        @Query("category_id") categoryID: String
    ): List<PostDto>

    @GET("ContentShare_Detail.php")
    suspend fun getPostDetail(
        @Query("share_id") shareID: String
    ): PostDetailResponse

    @Multipart
    @POST("upload_content_share.php")
    suspend fun uploadPost(
        @Part("user_id") userId: RequestBody,
        @Part("category_id") categoryId: RequestBody,
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody,
        @Part("location") location: RequestBody,
        @Part image: MultipartBody.Part
    ): DodamDuckResponse

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
    suspend fun getCategories(): List<CategoryDto>

    @HTTP(method = "DELETE", path = "ShareContentDelete.php", hasBody = true)
    @FormUrlEncoded
    suspend fun deletePost(
        @Field("share_id") shareId: String,
        @Field("user_id") userId: String
    ): DodamDuckResponse

    @POST("create_chat_room.php")
    @FormUrlEncoded
    suspend fun createChat(
        @Field("post_id") postId: String,
        @Field("user_id") userId: String
    ): DodamDuckResponse

    @GET("SearchQuery.php")
    suspend fun searchPost(
        @Query("query") query: String
    ): List<PostDto>

}