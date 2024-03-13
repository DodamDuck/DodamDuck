package org.chosun.dodamduck.presentation.post

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostCategories
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostList
import org.chosun.dodamduck.domain.usecase.remote.post.UploadPost
import org.chosun.dodamduck.domain.usecase.remote.post.UploadViewCount
import org.chosun.dodamduck.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostCategories: GetPostCategories<PostDTO>,
    private val getPostList: GetPostList<PostDTO>,
    private val uploadPostUseCase: UploadPost<PostDTO>,
    private val uploadViewCountUseCase: UploadViewCount<PostDTO>,
) : BaseViewModel<PostState, PostEvent, PostSideEffect>(
    PostReducer(PostState.init())
) {
    fun getPostLists() {
        viewModelScope.launch {
            sendEvent(PostEvent.OnLoading)
            getPostList().collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        sendEvent(PostEvent.OnSuccessPostList(apiResult.value))
                    }

                    is ApiResult.Error -> sendEvent(PostEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            viewModelScope.launch {
                sendEvent(PostEvent.OnLoading)
                getPostCategories().collectLatest { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success -> {
                            sendEvent(PostEvent.OnSuccessPostCategories(apiResult.value))
                        }

                        is ApiResult.Error -> sendEvent(PostEvent.OnError)

                        is ApiResult.Exception -> {}
                    }
                }
            }
        }
    }

    fun uploadPost(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ) {
        viewModelScope.launch {
            sendEvent(PostEvent.OnLoading)
            uploadPostUseCase(userId, categoryId, title, content, location, image)
                .collectLatest { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success -> {
                            sendEvent(PostEvent.OnSuccess)
                            setEffect(PostSideEffect.NavigatePopBackStack)
                        }

                        is ApiResult.Error -> sendEvent(PostEvent.OnError)

                        is ApiResult.Exception -> {}
                    }
                }
        }
    }

    fun getPostLists(categoryID: String) {
        viewModelScope.launch {
            sendEvent(PostEvent.OnLoading)
            getPostList(categoryID).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        sendEvent(PostEvent.OnSuccessPostList(apiResult.value))
                    }

                    is ApiResult.Error -> sendEvent(PostEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun uploadViewCount(postID: String) {
        viewModelScope.launch {
            sendEvent(PostEvent.OnLoading)
            uploadViewCountUseCase(postID).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        sendEvent(PostEvent.OnSuccess)
                    }

                    is ApiResult.Error -> sendEvent(PostEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }
}