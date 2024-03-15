package org.chosun.dodamduck.presentation.detail.base

import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.presentation.base.State

data class PostDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val postDetail: PostDetailResponse? = null
): State {
    companion object {
        fun init() = PostDetailState()
    }

}