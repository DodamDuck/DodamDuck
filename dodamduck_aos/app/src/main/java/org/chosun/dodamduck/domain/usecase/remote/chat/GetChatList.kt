package org.chosun.dodamduck.domain.usecase.remote.chat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.data.dto.ChatListDTO
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatList @Inject constructor(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(
        userId: String
    ): Flow<ApiResult<List<ChatListDTO>?>> = channelFlow {
        chatRepository.fetchChatList(userId).collectLatest {  apiResult ->
            if (apiResult is ApiResult.Success) {
                send(ApiResult.Success(apiResult.value.chat_list))
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