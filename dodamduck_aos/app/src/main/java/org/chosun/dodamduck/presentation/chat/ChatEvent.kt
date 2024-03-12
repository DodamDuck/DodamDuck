package org.chosun.dodamduck.presentation.chat

import org.chosun.dodamduck.data.dto.ChatInfo
import org.chosun.dodamduck.data.dto.ChatListDTO
import org.chosun.dodamduck.presentation.base.Event

sealed class ChatEvent : Event {

    object OnLoadingChats : ChatEvent()
    data class OnSuccessChats(val chats: List<ChatInfo>) : ChatEvent()

    object OnEmptyChats : ChatEvent()

    object OnErrorChats : ChatEvent()

    object OnLoadingChatList : ChatEvent()

    data class OnSuccessChatList(val chatList: List<ChatListDTO>) : ChatEvent()

    object OnEmptyChatList : ChatEvent()

    object OnErrorChatList : ChatEvent()

    object OnLoadingSendChat : ChatEvent()

    object OnErrorSendChat : ChatEvent()

    object OnSuccessSendChat : ChatEvent()
}
