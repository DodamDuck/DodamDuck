package org.chosun.dodamduck.model.dto

data class TradeDetailResponse(
    val post: TradeDetail,
    val comments: List<TradeDetailComments>
)

data class TradeDetail(
    val title: String,
    val content: String,
    val views: String,
    val userName: String,
    val location: String,
    val verification_count: String,
    val image_url: String,
    val category: String,
    val user_id: String
)

data class TradeDetailComments(
    val content: String,
    val user_id: String,
    val userName: String,
    val verification_count: String,
    val location: String,
    val created_at: String
)
