package org.chosun.dodamduck.presentation.post

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.data.dto.post.PostUseCaseDto
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostCategories
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostList
import org.chosun.dodamduck.domain.usecase.remote.post.UploadPost
import org.chosun.dodamduck.domain.usecase.remote.post.UploadViewCount
import org.chosun.dodamduck.presentation.base.BaseViewModel
import org.chosun.dodamduck.presentation.processApiResult
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostCategories: GetPostCategories<PostDto>,
    private val getPostList: GetPostList<PostDto>,
    private val uploadPostUseCase: UploadPost<PostDto>,
    private val uploadViewCountUseCase: UploadViewCount<PostDto>,
) : BaseViewModel<PostState, PostEvent, PostSideEffect>(
    PostReducer(PostState.init())
) {
    fun getPostLists() {
        viewModelScope.processApiResult(getPostList()) {
            onLoading { sendEvent(PostEvent.OnLoading) }
            onSuccess { data -> sendEvent(PostEvent.OnSuccessPostList(data)) }
            onError { sendEvent(PostEvent.OnError) }
        }
    }

    fun getCategories() {
        viewModelScope.processApiResult(getPostCategories(null)) {
            onLoading { sendEvent(PostEvent.OnLoading) }
            onSuccess { data -> sendEvent(PostEvent.OnSuccessPostCategories(data)) }
            onError { sendEvent(PostEvent.OnError) }
        }
    }

    fun uploadPost(
        userId: String,
        categoryId: String,
        title: String,
        content: String,
        location: String,
        image: MultipartBody.Part
    ) {
        viewModelScope.processApiResult(
            uploadPostUseCase(
                PostUseCaseDto(
                    userId = userId,
                    categoryId = categoryId,
                    title = title,
                    content = content,
                    location = location,
                    image = image
                )
            )
        ) {
            onLoading { sendEvent(PostEvent.OnLoading) }
            onSuccess {
                sendEvent(PostEvent.OnSuccess)
                setEffect(PostSideEffect.NavigatePopBackStack)
            }
            onError { sendEvent(PostEvent.OnError) }
        }
    }

    fun getPostLists(categoryID: String) {
        viewModelScope.processApiResult(getPostList(categoryID)) {
            onLoading { sendEvent(PostEvent.OnLoading) }
            onSuccess { data -> sendEvent(PostEvent.OnSuccessPostList(data)) }
            onError { sendEvent(PostEvent.OnError) }
        }
    }

    fun uploadViewCount(postId: String) {
        viewModelScope.processApiResult(uploadViewCountUseCase(postId)) {
            onLoading { sendEvent(PostEvent.OnLoading) }
            onSuccess { sendEvent(PostEvent.OnSuccess) }
            onError { sendEvent(PostEvent.OnError) }
        }
    }
}