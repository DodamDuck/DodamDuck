package org.chosun.dodamduck.presentation.post

import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.presentation.base.Event

sealed class PostEvent: Event {

    data class OnSuccessPostList(val postList: List<PostDTO>): PostEvent()

    data class OnSuccessPostCategories(val categories: List<CategoryDTO>): PostEvent()

    data class OnSuccessPostDetail(val postDetail: PostDetailResponse): PostEvent()

    object OnSuccess: PostEvent()

    object OnError: PostEvent()

    object OnLoading: PostEvent()
}