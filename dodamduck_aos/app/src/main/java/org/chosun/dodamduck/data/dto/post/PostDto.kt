package org.chosun.dodamduck.data.dto.post

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("Title") val title: String,
    @SerializedName("Content") val content: String,
    @SerializedName("ImageURL") val imageUrl: String,
    @SerializedName("CreatedAt") val createdAt: String,
    @SerializedName("Location") val location: String,
    @SerializedName("Views") val views: String,
    @SerializedName("CommentCount") val commentCount: String,
    @SerializedName("CategoryName") val categoryName: String,
    @SerializedName("ShareID") val shareID: String
)