package org.chosun.dodamduck.presentation.chat

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.remote.chat.GetChatList
import org.chosun.dodamduck.domain.usecase.remote.chat.GetChats
import org.chosun.dodamduck.domain.usecase.remote.chat.SendChat
import org.chosun.dodamduck.presentation.base.BaseViewModel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChats: GetChats,
    private val getChatList: GetChatList,
    private val sendChatUseCase: SendChat
) : BaseViewModel<ChatState, ChatEvent, ChatSideEffect>(
    ChatReducer(ChatState.init())
) {
    private val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    fun fetchChats(user1: String, user2: String) {
        scheduledExecutorService.scheduleAtFixedRate({
            sendEvent(ChatEvent.OnLoadingChats)
            viewModelScope.launch {
                getChats(Pair(user1, user2)).collectLatest { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success -> {
                            if (apiResult.value.isNotEmpty()) sendEvent(ChatEvent.OnSuccessChats(apiResult.value))
                            else sendEvent(ChatEvent.OnEmptyChats)
                        }

                        is ApiResult.Error-> sendEvent(ChatEvent.OnErrorChats)

                        is ApiResult.Exception -> { }
                    }
                }
            }
        }, 0, 1, TimeUnit.SECONDS)
    }

    fun fetchChatList(userId: String) {
        viewModelScope.launch {
            sendEvent(ChatEvent.OnLoadingChatList)
            getChatList(userId).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.chat_list != null) sendEvent(ChatEvent.OnSuccessChatList(apiResult.value.chat_list))
                        else sendEvent(ChatEvent.OnEmptyChatList)
                    }

                    is ApiResult.Error-> sendEvent(ChatEvent.OnErrorChatList)

                    is ApiResult.Exception -> { }
                }
            }
        }
    }

    fun sendChat(
        sender: String,
        receiver: String,
        message: String
    ) {
        viewModelScope.launch {
            sendEvent(ChatEvent.OnLoadingSendChat)
            sendChatUseCase(Triple(sender, receiver, message)).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> sendEvent(ChatEvent.OnSuccessSendChat)

                    is ApiResult.Error-> sendEvent(ChatEvent.OnErrorSendChat)

                    is ApiResult.Exception -> { }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scheduledExecutorService.shutdownNow()
    }
}
