package org.chosun.dodamduck.domain.usecase.remote.chat

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.network.response.ChatListResponse
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ChatRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class GetChatList @Inject constructor(
    private val chatRepository: ChatRepository
): BaseResultUseCase<String, ChatListResponse>(){

    override suspend fun execute(userId: String): Flow<ApiResult<ChatListResponse>> {
        return chatRepository.fetchChatList(userId)
    }
}