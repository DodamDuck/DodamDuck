package org.chosun.dodamduck.network

import org.chosun.dodamduck.data.dto.ChatInfo
import org.chosun.dodamduck.data.dto.ChatListResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatApiService {
    @GET("getMessage.php")
    suspend fun getChats(
        @Query("user1") user1: String,
        @Query("user2") user2: String,
    ): List<ChatInfo>?

    @FormUrlEncoded
    @POST("sendMessage.php")
    suspend fun sendChat(
        @Field("senderID") sender: String,
        @Field("receiverID") receiver: String,
        @Field("message") message: String
    ): DodamDuckResponse


    @GET("get_chat_list.php")
    suspend fun getChatList(
        @Query("user_id") user1: String
    ): ChatListResponse?
}