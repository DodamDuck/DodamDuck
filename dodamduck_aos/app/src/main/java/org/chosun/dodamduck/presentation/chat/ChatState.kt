package org.chosun.dodamduck.presentation.chat

import org.chosun.dodamduck.data.dto.ChatInfo
import org.chosun.dodamduck.data.dto.ChatListDTO
import org.chosun.dodamduck.presentation.base.State

data class ChatState(
    val chatDetailLoading: Boolean = false,
    val chatDetailSuccess: Boolean = false,
    val chatDetailError: Boolean = false,
    val chatListLoading: Boolean = false,
    val chatListSuccess: Boolean = false,
    val chatListError: Boolean = false,
    val chatSendLoading: Boolean = false,
    val chatSendSuccess: Boolean = false,
    val chatSendError: Boolean = false,
    val chats: List<ChatInfo> = emptyList(),
    val chatList: List<ChatListDTO> = emptyList()
): State {
    companion object {
        fun init() = ChatState()
    }
}