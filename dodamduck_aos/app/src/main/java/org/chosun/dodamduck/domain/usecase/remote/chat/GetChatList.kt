package org.chosun.dodamduck.domain.usecase.remote.chat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.data.dto.ChatListDTO
import org.chosun.dodamduck.data.dto.ChatListResponse
import org.chosun.dodamduck.data.dto.auth.AuthDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ChatRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class GetChatList @Inject constructor(
    private val chatRepository: ChatRepository
): BaseResultUseCase<String, ChatListResponse>(){

    override suspend fun execute(userId: String): Flow<ApiResult<ChatListResponse>> {
        return chatRepository.fetchChatList(userId)
    }
}