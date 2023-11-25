package org.chosun.dodamduck.model.dto

data class PostDetailResponse(
    val post: PostDetailDTO,
    val comments: List<PostCommentDTO>
)