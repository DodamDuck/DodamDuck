package org.chosun.dodamduck.model.dto

import com.google.gson.annotations.SerializedName

data class PostDTO(
    @SerializedName("ShareID") val shareID: String,
    @SerializedName("UserID") val userID: String,
    @SerializedName("CategoryID") val categoryID: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Content") val content: String,
    @SerializedName("ImageURL") val imageURL: String,
    @SerializedName("CreatedAt") val createdAt: String,
    @SerializedName("UpdatedAt") val updatedAt: String,
    @SerializedName("Location") val location: String,
    @SerializedName("Views") val views: String,
    @SerializedName("CommentCount") val commentCount: String,
    @SerializedName("CategoryName") val categoryName: String
)

data class PostCommentDTO(
    @SerializedName("CommentID") val commentID: String,
    @SerializedName("ShareID") val shareID: String,
    @SerializedName("UserID") val userID: String,
    @SerializedName("Comment") val comment: String,
    @SerializedName("CreatedAt") val createAt: String,
    @SerializedName("UpdatedAt") val updateAt: String
)
