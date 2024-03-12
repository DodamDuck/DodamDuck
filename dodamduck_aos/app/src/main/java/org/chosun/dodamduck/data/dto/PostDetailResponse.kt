package org.chosun.dodamduck.data.dto

data class PostDetailResponse(
    val post: PostDetailDTO,
    val comments: List<PostCommentDTO>
)