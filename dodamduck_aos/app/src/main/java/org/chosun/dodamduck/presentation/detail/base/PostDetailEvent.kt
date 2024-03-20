package org.chosun.dodamduck.presentation.detail.base

import org.chosun.dodamduck.network.response.PostDetailResponse
import org.chosun.dodamduck.presentation.base.Event

sealed class PostDetailEvent: Event {
    data class OnSuccessPostDetail(val postDetail: PostDetailResponse): PostDetailEvent()

    object OnSuccess: PostDetailEvent()

    object OnError: PostDetailEvent()

    object OnLoading: PostDetailEvent()
}