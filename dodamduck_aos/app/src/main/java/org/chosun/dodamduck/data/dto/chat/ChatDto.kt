package org.chosun.dodamduck.data.dto.chat

data class ChatDto(
    val id: String,
    val senderID: String,
    val receiverID: String,
    val senderName: String,
    val receiverName: String,
    val message: String,
    val timestamp: String
)