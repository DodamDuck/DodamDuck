package org.chosun.dodamduck.presentation.post

import org.chosun.dodamduck.presentation.base.Reducer

class PostReducer(state: PostState): Reducer<PostState, PostEvent>(state) {
    override fun reduce(oldState: PostState, event: PostEvent) {
        when(event) {
            is PostEvent.OnSuccessPostList -> setState(oldState.copy(isLoading = false, postList = event.postList))
            is PostEvent.OnSuccessPostCategories -> setState(oldState.copy(isLoading = false, categories = event.categories))
            is PostEvent.OnSuccessPostDetail -> setState(oldState.copy(isLoading = false, postDetail = event.postDetail))
            is PostEvent.OnSuccess -> setState(oldState.copy(isLoading = false))
            is PostEvent.OnLoading -> setState(oldState.copy(isLoading = true, isError = false))
            is PostEvent.OnError -> setState(oldState.copy(isLoading = false, isError = true))
        }
    }
}