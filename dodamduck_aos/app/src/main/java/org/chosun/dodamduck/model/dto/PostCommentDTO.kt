package org.chosun.dodamduck.model.dto


data class PostCommentDTO(
    val comment_id: String,
    val shareID: String,
    val userID: String,
    val content: String,
    val created_at: String,
    val update_at: String,
    val userName: String,
    val verification_count: String,
    val location: String,
    val profile_url: String
)