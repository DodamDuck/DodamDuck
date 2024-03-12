package org.chosun.dodamduck.data.dto

data class ChatListDTO(
    val chat_id: String,
    val user1_id: String,
    val user2_id: String,
    val user1_name: String,
    val user2_name: String,
    val last_message: String,
    val last_message_timestamp: String,
    val post_title: String,
    val post_image_url: String,
    val seller_profile_url: String,
    val category: String
)

data class ChatListResponse(
    val error: Boolean,
    val chat_list: List<ChatListDTO>?
)