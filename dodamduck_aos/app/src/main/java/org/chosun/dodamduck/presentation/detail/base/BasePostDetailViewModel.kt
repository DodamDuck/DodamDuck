package org.chosun.dodamduck.presentation.detail.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.chosun.dodamduck.data.dto.post.PostUseCaseDto
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
                        sendEvent(PostDetailEvent.OnSuccessPostDetail(apiResult.value))
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
            createChatUseCase(PostUseCaseDto(postId, userId)).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.error == "false") {
                            sendEvent(PostDetailEvent.OnSuccess)
                            setEffect(PostDetailSideEffect.NavigateToChatList)
                        }
                        else {
                            sendEvent(PostDetailEvent.OnError)
                            if(apiResult.value.message == "Chat room already exists.")
                              setEffect(PostDetailSideEffect.Toast("이미 존재하는 채팅이 있습니다."))
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
            deletePostUseCase(Pair(postID, userID)).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.error == "false") {
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
            uploadCommentUseCase(Triple(postID, userID, comment)).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.error == "false") sendEvent(PostDetailEvent.OnSuccess)
                        else sendEvent(PostDetailEvent.OnError)
                    }

                    is ApiResult.Error -> sendEvent(PostDetailEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }
}