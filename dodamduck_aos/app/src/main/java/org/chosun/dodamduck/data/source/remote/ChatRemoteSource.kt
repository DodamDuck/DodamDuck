package org.chosun.dodamduck.data.source.remote

import org.chosun.dodamduck.network.ChatApiService
import javax.inject.Inject

class ChatRemoteSource @Inject constructor(
    private val chatApiService: ChatApiService
) {
    suspend fun fetchChats(
        user1: String,
        user2: String
    ) = chatApiService.getChats(user1, user2)

    suspend fun sendChat(
        sender: String,
        receiver: String,
        message: String
    ) = chatApiService.sendChat(sender, receiver, message)

    suspend fun fetchChatList(
        userId: String
    ) = chatApiService.getChatList(userId)
}