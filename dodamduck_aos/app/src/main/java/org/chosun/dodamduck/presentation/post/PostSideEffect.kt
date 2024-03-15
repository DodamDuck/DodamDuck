package org.chosun.dodamduck.presentation.post

import org.chosun.dodamduck.presentation.base.SideEffect

sealed class PostSideEffect: SideEffect {
    data class Toast(val text: String): PostSideEffect()

    object NavigateToChatList: PostSideEffect()

    object NavigatePopBackStack: PostSideEffect()
}