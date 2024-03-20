package org.chosun.dodamduck.data.dto.post

import com.google.gson.annotations.SerializedName


data class PostCommentDto(
    @SerializedName("comment_id")
    val commentId: String,
    val shareID: String,
    val userID: String,
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("update_at")
    val updateAt: String,
    val userName: String,
    @SerializedName("verification_count")
    val verificationCount: String,
    val location: String,
    @SerializedName("profile_url")
    val profileUrl: String
)