package org.chosun.dodamduck.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.ChatInfo
import org.chosun.dodamduck.model.dto.ChatListDTO
import org.chosun.dodamduck.model.repository.ChatRepository
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    private val _chats = MutableStateFlow<List<ChatInfo>?>(null)
    val chats: StateFlow<List<ChatInfo>?> = _chats

    private val _chatList = MutableStateFlow<List<ChatListDTO>?>(null)
    val chatList: StateFlow<List<ChatListDTO>?> = _chatList

    private val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    suspend fun getChats(user1: String, user2: String) {
        scheduledExecutorService.scheduleAtFixedRate({
            viewModelScope.launch {
                _chats.value = repository.fetchChats(user1, user2)
            }
        }, 0, 1, TimeUnit.SECONDS)
    }

    suspend fun getChatList(userId: String) {
        viewModelScope.launch {
            _chatList.value = repository.fetchChatList(userId)
        }
    }

    fun sendChat(
        sender: String,
        receiver: String,
        message: String
    ) {
        viewModelScope.launch {
            repository.sendChat(sender, receiver, message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scheduledExecutorService.shutdownNow()
    }
}
