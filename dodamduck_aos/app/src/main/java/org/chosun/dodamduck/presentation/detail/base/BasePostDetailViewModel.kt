package org.chosun.dodamduck.presentation.detail.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.remote.post.CreateChat
import org.chosun.dodamduck.domain.usecase.remote.post.DeletePost
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostDetail
import org.chosun.dodamduck.domain.usecase.remote.post.UploadComment
import org.chosun.dodamduck.presentation.base.BaseViewModel

abstract class BasePostDetailViewModel<T>(
    private val getPostDetailUseCase: GetPostDetail<T>,
    private val createChatUseCase: CreateChat<T>,
    private val deletePostUseCase: DeletePost<T>,
    private val uploadCommentUseCase: UploadComment<T>
) : BaseViewModel<PostDetailState, PostDetailEvent, PostDetailSideEffect>(
    PostDetailReducer(PostDetailState.init())
) {
    fun fetchDetail(contentId: String) {
        viewModelScope.launch {
            sendEvent(PostDetailEvent.OnLoading)
            getPostDetailUseCase(contentId).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        sendEvent(PostDetailEvent.OnSuccessPostDetail(apiResult.value!!))
                    }

                    is ApiResult.Error -> sendEvent(PostDetailEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun createChat(postId: String, userId: String) {
        viewModelScope.launch {
            sendEvent(PostDetailEvent.OnLoading)
            createChatUseCase(postId, userId).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.error == "false") {
                            sendEvent(PostDetailEvent.OnSuccess)
                            setEffect(PostDetailSideEffect.NavigateToChatList)
                        }
                        else {
                            sendEvent(PostDetailEvent.OnError)
                            setEffect(PostDetailSideEffect.Toast("채팅방 생성 중 오류가 발생 했습니다."))
                        }
                    }

                    is ApiResult.Error -> sendEvent(PostDetailEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun deletePost(postID: String, userID: String) {
        viewModelScope.launch {
            sendEvent(PostDetailEvent.OnLoading)
            deletePostUseCase(postID, userID).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value?.error == "false") {
                            sendEvent(PostDetailEvent.OnSuccess)
                            setEffect(PostDetailSideEffect.Toast("게시글이 삭제 되었습니다."))
                            setEffect(PostDetailSideEffect.NavigatePopBackStack)
                        }
                        else {
                            sendEvent(PostDetailEvent.OnError)
                            setEffect(PostDetailSideEffect.Toast("게시글 작성자만 삭제 할 수 있습니다."))
                        }
                    }

                    is ApiResult.Error -> sendEvent(PostDetailEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun uploadComment(postID: String, userID: String, comment: String) {
        viewModelScope.launch {
            sendEvent(PostDetailEvent.OnLoading)
            uploadCommentUseCase(postID, userID, comment).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value?.error == "false") sendEvent(PostDetailEvent.OnSuccess)
                        else sendEvent(PostDetailEvent.OnError)
                    }

                    is ApiResult.Error -> sendEvent(PostDetailEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }
}