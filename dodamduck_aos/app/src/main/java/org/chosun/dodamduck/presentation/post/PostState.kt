package org.chosun.dodamduck.presentation.post

import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.network.response.PostDetailResponse
import org.chosun.dodamduck.presentation.base.State

data class PostState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val categories: List<CategoryDto> = emptyList(),
    val postList: List<PostDto> = emptyList(),
    val postDetail: PostDetailResponse? = null
) : State {
    companion object {
        fun init() = PostState()
    }
}