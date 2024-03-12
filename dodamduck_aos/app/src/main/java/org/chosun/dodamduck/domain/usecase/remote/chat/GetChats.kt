package org.chosun.dodamduck.domain.usecase.remote.chat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.data.dto.ChatInfo
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ChatRepository
import javax.inject.Inject

class GetChats @Inject constructor(
    private val chatRepository: ChatRepository
) {
    operator fun invoke(
        user1: String,
        user2: String
    ): Flow<ApiResult<List<ChatInfo>>> = channelFlow {
        chatRepository.fetchChats(user1, user2).collectLatest { apiResult ->
            if (apiResult is ApiResult.Success) {
                send(ApiResult.Success(apiResult.value))
            } else {
                if (apiResult is ApiResult.Error) {
                    send(apiResult)
                } else if (apiResult is ApiResult.Exception) {
                    send(apiResult)
                }
            }
        }
    }
}