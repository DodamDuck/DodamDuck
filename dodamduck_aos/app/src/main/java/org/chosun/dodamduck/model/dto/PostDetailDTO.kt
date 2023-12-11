package org.chosun.dodamduck.model.dto

data class PostDetailDTO(
    val title: String,
    val content: String,
    val image_url: String,
    val created_at: String,
    val location: String,
    val views: String,
    val userName: String,
    val user_id: String,
    val verification_count: String,
    val categoryName: String,
    val profile_url: String
)