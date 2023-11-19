package org.chosun.dodamduck.model.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.model.dto.Trade
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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
}