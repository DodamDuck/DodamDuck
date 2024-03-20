package org.chosun.dodamduck.domain.usecase.remote.chat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ChatRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class SendChat @Inject constructor(
  private val chatRepository: ChatRepository
): BaseResultUseCase<Triple<String, String, String>, DodamDuckResponse>() {

    override suspend fun execute(params: Triple<String, String, String>): Flow<ApiResult<DodamDuckResponse>> {
        return chatRepository.sendChat(params.first, params.second, params.third)
    }
}