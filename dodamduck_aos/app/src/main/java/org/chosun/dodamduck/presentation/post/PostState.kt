package org.chosun.dodamduck.presentation.post

import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.presentation.base.State

data class PostState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val categories: List<CategoryDTO> = emptyList(),
    val postList: List<PostDTO> = emptyList(),
    val postDetail: PostDetailResponse? = null
) : State {
    companion object {
        fun init() = PostState()
    }
}