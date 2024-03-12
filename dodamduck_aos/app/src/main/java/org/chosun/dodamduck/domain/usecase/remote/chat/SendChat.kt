package org.chosun.dodamduck.domain.usecase.remote.chat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ChatRepository
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class SendChat @Inject constructor(
  private val chatRepository: ChatRepository
) {

    operator fun invoke(
        sender: String,
        receiver: String,
        message: String
    ): Flow<ApiResult<DodamDuckResponse>> = channelFlow {
        chatRepository.sendChat(sender, receiver, message).collectLatest { apiResult ->
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