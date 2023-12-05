package org.chosun.dodamduck.model.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.dto.Trade
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface TradeApiService {
    @GET("Post.php")
    suspend fun getTradeList(): List<Trade>?

    @Multipart
    @POST("PostWrite.php")
    suspend fun uploadTrade(
        @Part("user_id") userId: RequestBody,
        @Part("category_id") categoryId: RequestBody,
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody,
        @Part("location") location: RequestBody,
        @Part image: MultipartBody.Part
    ): DodamDuckResponse


    @POST("PostDetail.php")
    @FormUrlEncoded
    suspend fun getTradeDetail(
        @Field("post_id") postId: String
    ): PostDetailResponse

    @POST("upload_comments.php")
    @FormUrlEncoded
    suspend fun uploadComment(
        @Field("post_id") postId: String,
        @Field("user_id") userId: String,
        @Field("content") content: String
    ): DodamDuckResponse

    @POST("upload_post_view_up.php")
    @FormUrlEncoded
    suspend fun uploadViews(
        @Field("post_id") postId: String
    ): DodamDuckResponse

    @HTTP(method = "DELETE", path = "PostDelete.php", hasBody = true)
    @FormUrlEncoded
    suspend fun deleteTrade(
        @Field("post_id") postId: String,
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
    ): List<Trade>

}