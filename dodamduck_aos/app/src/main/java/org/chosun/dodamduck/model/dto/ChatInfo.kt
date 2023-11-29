package org.chosun.dodamduck.model.dto

data class ChatInfo(
    val id: String,
    val senderID: String,
    val receiverID: String,
    val senderName: String,
    val receiverName: String,
    val message: String,
    val timestamp: String
)