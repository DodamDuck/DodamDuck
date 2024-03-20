package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.chat.ChatDto
import org.chosun.dodamduck.network.response.ChatListResponse
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.network.response.DodamDuckResponse

interface ChatRepository {
    suspend fun fetchChats(
        user1: String,
        user2: String
    ): Flow<ApiResult<List<ChatDto>>>

    suspend fun sendChat(
        sender: String,
        receiver: String,
        message: String
    ): Flow<ApiResult<DodamDuckResponse>>

    suspend fun fetchChatList(
        userId: String
    ): Flow<ApiResult<ChatListResponse>>
}