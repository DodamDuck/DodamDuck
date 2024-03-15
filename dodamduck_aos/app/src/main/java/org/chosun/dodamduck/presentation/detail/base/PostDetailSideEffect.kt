package org.chosun.dodamduck.presentation.detail.base

import org.chosun.dodamduck.presentation.base.SideEffect

sealed class PostDetailSideEffect: SideEffect {
    data class Toast(val text: String): PostDetailSideEffect()

    object NavigateToChatList: PostDetailSideEffect()

    object NavigatePopBackStack: PostDetailSideEffect()
}