package org.chosun.dodamduck.data.dto.trade

import com.google.gson.annotations.SerializedName

data class Trade(
    @SerializedName("post_id")
    val postId: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("category_id")
    val categoryId: String,
    val title: String,
    val content: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val location: String,
    val views: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("comment_count")
    val commentCount: String
)