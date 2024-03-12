package org.chosun.dodamduck.data.dto

data class Trade(
    val post_id: String,
    val user_id: String,
    val category_id: String,
    val title: String,
    val content: String,
    val image_url: String,
    val created_at: String,
    val updated_at: String,
    val location: String,
    val views: String,
    val category_name: String,
    val comment_count: String
)