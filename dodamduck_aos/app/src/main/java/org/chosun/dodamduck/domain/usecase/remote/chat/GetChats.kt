package org.chosun.dodamduck.domain.usecase.remote.chat

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.chat.ChatDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ChatRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class GetChats @Inject constructor(
    private val chatRepository: ChatRepository
): BaseResultUseCase<Pair<String, String>, List<ChatDto>>(){
    override suspend fun execute(params: Pair<String, String>): Flow<ApiResult<List<ChatDto>>> {
        return chatRepository.fetchChats(params.first, params.second)
    }
}