package org.chosun.dodamduck.presentation.detail.base

import org.chosun.dodamduck.presentation.base.Reducer

class PostDetailReducer(state: PostDetailState): Reducer<PostDetailState, PostDetailEvent>(state) {
    override fun reduce(oldState: PostDetailState, event: PostDetailEvent) {
        when(event) {
            is PostDetailEvent.OnSuccessPostDetail -> setState(oldState.copy(isLoading = false, postDetail = event.postDetail))
            is PostDetailEvent.OnSuccess -> setState(oldState.copy(isLoading = false))
            is PostDetailEvent.OnLoading -> setState(oldState.copy(isLoading = true, isError = false))
            is PostDetailEvent.OnError -> setState(oldState.copy(isLoading = false, isError = true))

        }
    }
}