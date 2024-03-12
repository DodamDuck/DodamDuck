package org.chosun.dodamduck.presentation.post.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.remote.post.CreateChat
import org.chosun.dodamduck.domain.usecase.remote.post.DeletePost
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostDetail
import org.chosun.dodamduck.domain.usecase.remote.post.UploadComment
import org.chosun.dodamduck.presentation.base.BaseViewModel
import org.chosun.dodamduck.presentation.post.PostEvent
import org.chosun.dodamduck.presentation.post.PostReducer
import org.chosun.dodamduck.presentation.post.PostSideEffect
import org.chosun.dodamduck.presentation.post.PostState
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetail<PostDTO>,
    private val createChatUseCase: CreateChat<PostDTO>,
    private val deletePostUseCase: DeletePost<PostDTO>,
    private val uploadCommentUseCase: UploadComment<PostDTO>
) : BaseViewModel<PostState, PostEvent, PostSideEffect>(
    PostReducer(PostState.init())
) {
    fun fetchDetail(contentId: String) {
        viewModelScope.launch {
            sendEvent(PostEvent.OnLoading)
            getPostDetailUseCase(contentId).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        sendEvent(PostEvent.OnSuccessPostDetail(apiResult.value!!))
                    }

                    is ApiResult.Error -> sendEvent(PostEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun createChat(postId: String, userId: String) {
        viewModelScope.launch {
            sendEvent(PostEvent.OnLoading)
            createChatUseCase(postId, userId).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.error == "false") {
                            sendEvent(PostEvent.OnSuccess)
                            setEffect(PostSideEffect.NavigateToChatList)
                        }
                        else {
                            sendEvent(PostEvent.OnError)
                            setEffect(PostSideEffect.Toast("채팅방 생성 중 오류가 발생 했습니다."))
                        }
                    }

                    is ApiResult.Error -> sendEvent(PostEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun deletePost(postID: String, userID: String) {
        viewModelScope.launch {
            sendEvent(PostEvent.OnLoading)
            deletePostUseCase(postID, userID).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value?.error == "false") {
                            sendEvent(PostEvent.OnSuccess)
                            setEffect(PostSideEffect.Toast("게시글이 삭제 되었습니다."))
                            setEffect(PostSideEffect.NavigatePopBackStack)
                        }
                        else {
                            sendEvent(PostEvent.OnError)
                            setEffect(PostSideEffect.Toast("게시글 작성자만 삭제 할 수 있습니다."))
                        }
                    }

                    is ApiResult.Error -> sendEvent(PostEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun uploadComment(postID: String, userID: String, comment: String) {
        viewModelScope.launch {
            sendEvent(PostEvent.OnLoading)
            uploadCommentUseCase(postID, userID, comment).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value?.error == "false") sendEvent(PostEvent.OnSuccess)
                        else sendEvent(PostEvent.OnError)
                    }

                    is ApiResult.Error -> sendEvent(PostEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }
}