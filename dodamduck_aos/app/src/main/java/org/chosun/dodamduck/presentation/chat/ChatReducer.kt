package org.chosun.dodamduck.presentation.chat

import org.chosun.dodamduck.presentation.base.Reducer

class ChatReducer(state: ChatState): Reducer<ChatState, ChatEvent>(state) {
    override fun reduce(oldState: ChatState, event: ChatEvent) {
        when(event) {
            is ChatEvent.OnSuccessChats -> setState(oldState.copy(chatDetailSuccess = true, chatDetailLoading = false, chats = event.chats))
            is ChatEvent.OnEmptyChats -> setState(oldState.copy(chatDetailSuccess = true, chatDetailLoading = false, chats = emptyList()))
            is ChatEvent.OnErrorChats -> setState(oldState.copy(chatDetailSuccess = false, chatDetailLoading = false, chatDetailError = true))
            is ChatEvent.OnLoadingChats -> setState(oldState.copy(chatDetailLoading = true))
            is ChatEvent.OnSuccessChatList -> setState(oldState.copy(chatListSuccess = true, chatListLoading = false, chatList = event.chatList))
            is ChatEvent.OnEmptyChatList -> setState(oldState.copy(chatListSuccess = true, chatListLoading = false, chatList = emptyList()))
            is ChatEvent.OnErrorChatList -> setState(oldState.copy(chatListSuccess = false, chatListLoading = false, chatListError = true))
            is ChatEvent.OnLoadingChatList -> setState(oldState.copy(chatListLoading = true))
            is ChatEvent.OnSuccessSendChat -> setState(oldState.copy(chatSendSuccess = true, chatSendLoading = false))
            is ChatEvent.OnErrorSendChat -> setState(oldState.copy(chatSendSuccess = false, chatSendLoading = false))
            is ChatEvent.OnLoadingSendChat -> setState(oldState.copy(chatSendLoading = true))
        }
    }

}