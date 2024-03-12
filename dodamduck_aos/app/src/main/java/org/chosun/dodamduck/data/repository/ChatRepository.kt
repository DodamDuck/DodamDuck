package org.chosun.dodamduck.data.repository

import org.chosun.dodamduck.data.dto.ChatInfo
import org.chosun.dodamduck.data.dto.ChatListDTO
import org.chosun.dodamduck.network.ChatApiService
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val service: ChatApiService?
) {
    suspend fun fetchChats(
        user1: String,
        user2: String
    ): List<ChatInfo> {
        return service?.getChats(user1, user2) ?: listOf()
    }

    suspend fun sendChat(
        sender: String,
        receiver: String,
        message: String
    ) {
        service?.sendChat(sender, receiver, message)
    }

    suspend fun fetchChatList(
        userId: String
    ): List<ChatListDTO> {
        return service?.getChatList(userId)?.chat_list ?: listOf()
    }
}