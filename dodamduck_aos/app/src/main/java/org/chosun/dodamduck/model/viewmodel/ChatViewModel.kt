package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.ChatInfo
import org.chosun.dodamduck.model.repository.ChatRepository
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    private val _chatLists = MutableStateFlow<List<ChatInfo>?>(null)
    val chatLists: StateFlow<List<ChatInfo>?> = _chatLists

    private val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    suspend fun getChatLists(user1: String, user2: String) {
        scheduledExecutorService.scheduleAtFixedRate({
            viewModelScope.launch {
                _chatLists.value = repository.fetchChatList(user1, user2)
            }
        }, 0, 1, TimeUnit.SECONDS)
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
