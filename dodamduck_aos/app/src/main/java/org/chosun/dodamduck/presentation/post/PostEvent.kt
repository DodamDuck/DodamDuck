package org.chosun.dodamduck.presentation.post

import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.network.response.PostDetailResponse
import org.chosun.dodamduck.presentation.base.Event

sealed class PostEvent: Event {

    data class OnSuccessPostList(val postList: List<PostDto>): PostEvent()

    data class OnSuccessPostCategories(val categories: List<CategoryDto>): PostEvent()

    data class OnSuccessPostDetail(val postDetail: PostDetailResponse): PostEvent()

    object OnSuccess: PostEvent()

    object OnError: PostEvent()

    object OnLoading: PostEvent()
}