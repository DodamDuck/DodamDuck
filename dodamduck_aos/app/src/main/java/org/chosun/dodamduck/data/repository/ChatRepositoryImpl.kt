package org.chosun.dodamduck.data.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.ChatInfo
import org.chosun.dodamduck.data.dto.ChatListResponse
import org.chosun.dodamduck.data.safeFlow
import org.chosun.dodamduck.data.source.remote.ChatRemoteSource
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ChatRepository
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatRemoteSource: ChatRemoteSource
) : ChatRepository {
    override suspend fun fetchChats(
        user1: String,
        user2: String
    ): Flow<ApiResult<List<ChatInfo>>> = safeFlow {
        chatRemoteSource.fetchChats(user1, user2) ?: emptyList()
    }

    override suspend fun sendChat(
        sender: String,
        receiver: String,
        message: String
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        chatRemoteSource.sendChat(sender, receiver, message)
    }

    override suspend fun fetchChatList(
        userId: String
    ): Flow<ApiResult<ChatListResponse>> = safeFlow {
        chatRemoteSource.fetchChatList(userId)
    }
}