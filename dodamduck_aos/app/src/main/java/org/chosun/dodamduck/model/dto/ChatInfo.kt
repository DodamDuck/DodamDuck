package org.chosun.dodamduck.model.dto

data class ChatInfo(
    val id: String,
    val senderID: String,
    val receiverID: String,
    val message: String,
    val timestamp: String
)