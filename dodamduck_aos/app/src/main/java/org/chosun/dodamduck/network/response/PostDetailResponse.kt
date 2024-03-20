package org.chosun.dodamduck.network.response

import org.chosun.dodamduck.data.dto.post.PostDetailDto
import org.chosun.dodamduck.data.dto.post.PostCommentDto

data class PostDetailResponse(
    val post: PostDetailDto,
    val comments: List<PostCommentDto>
)