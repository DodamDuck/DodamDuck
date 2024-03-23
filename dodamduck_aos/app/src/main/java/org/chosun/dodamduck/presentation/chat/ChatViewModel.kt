package org.chosun.dodamduck.presentation.chat

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.chosun.dodamduck.domain.usecase.remote.chat.GetChatList
import org.chosun.dodamduck.domain.usecase.remote.chat.GetChats
import org.chosun.dodamduck.domain.usecase.remote.chat.SendChat
import org.chosun.dodamduck.presentation.base.BaseViewModel
import org.chosun.dodamduck.presentation.processApiResult
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
            viewModelScope.processApiResult(getChats(Pair(user1, user2))) {
                onLoading { sendEvent(ChatEvent.OnLoadingChats) }
                onSuccess { data ->
                    if (data.isNotEmpty()) sendEvent(ChatEvent.OnSuccessChats(data))
                    else sendEvent(ChatEvent.OnEmptyChats)
                }
                onError { sendEvent(ChatEvent.OnErrorChats) }
            }
        }, 0, 1, TimeUnit.SECONDS)
    }

    fun fetchChatList(userId: String) {
        viewModelScope.processApiResult(getChatList(userId)) {
            onLoading { sendEvent(ChatEvent.OnLoadingChatList) }
            onSuccess { data ->
                if (data.chat_list != null) sendEvent(ChatEvent.OnSuccessChatList(data.chat_list))
                else sendEvent(ChatEvent.OnEmptyChatList)
            }
            onError { sendEvent(ChatEvent.OnErrorChatList) }
        }
    }

    fun sendChat(
        sender: String,
        receiver: String,
        message: String
    ) {
        viewModelScope.processApiResult(sendChatUseCase(Triple(sender, receiver, message))) {
            onLoading { sendEvent(ChatEvent.OnLoadingSendChat) }
            onSuccess { sendEvent(ChatEvent.OnSuccessSendChat) }
            onError { sendEvent(ChatEvent.OnErrorSendChat) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scheduledExecutorService.shutdownNow()
    }
}
