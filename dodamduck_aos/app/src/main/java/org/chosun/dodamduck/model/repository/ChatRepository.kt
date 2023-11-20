package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.ChatInfo
import org.chosun.dodamduck.model.network.ChatApiService
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val service: ChatApiService?
) {
    suspend fun fetchChatList(
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
}