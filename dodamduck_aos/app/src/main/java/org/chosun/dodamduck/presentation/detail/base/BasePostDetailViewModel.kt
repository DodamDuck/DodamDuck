package org.chosun.dodamduck.presentation.detail.base

import androidx.lifecycle.viewModelScope
import org.chosun.dodamduck.data.dto.post.PostUseCaseDto
import org.chosun.dodamduck.domain.usecase.remote.post.CreateChat
import org.chosun.dodamduck.domain.usecase.remote.post.DeletePost
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostDetail
import org.chosun.dodamduck.domain.usecase.remote.post.UploadComment
import org.chosun.dodamduck.presentation.base.BaseViewModel
import org.chosun.dodamduck.presentation.processApiResult

abstract class BasePostDetailViewModel<T>(
    private val getPostDetailUseCase: GetPostDetail<T>,
    private val createChatUseCase: CreateChat<T>,
    private val deletePostUseCase: DeletePost<T>,
    private val uploadCommentUseCase: UploadComment<T>
) : BaseViewModel<PostDetailState, PostDetailEvent, PostDetailSideEffect>(
    PostDetailReducer(PostDetailState.init())
) {
    fun fetchDetail(contentId: String) {
        viewModelScope.processApiResult(getPostDetailUseCase(contentId)) {
            onLoading { sendEvent(PostDetailEvent.OnLoading) }
            onSuccess { data -> sendEvent(PostDetailEvent.OnSuccessPostDetail(data)) }
            onError { sendEvent(PostDetailEvent.OnError) }
        }
    }

    fun createChat(postId: String, userId: String) {
        viewModelScope.processApiResult(createChatUseCase(PostUseCaseDto(postId, userId))) {
            onLoading { sendEvent(PostDetailEvent.OnLoading) }
            onSuccess { data ->
                if (data.error == "false") {
                    sendEvent(PostDetailEvent.OnSuccess)
                    setEffect(PostDetailSideEffect.NavigateToChatList)
                }
                else {
                    sendEvent(PostDetailEvent.OnError)
                    if(data.message == "Chat room already exists.")
                        setEffect(PostDetailSideEffect.Toast("이미 존재하는 채팅이 있습니다."))
                }
            }
            onError { sendEvent(PostDetailEvent.OnError) }
        }
    }

    fun deletePost(postID: String, userID: String) {
        viewModelScope.processApiResult(deletePostUseCase(Pair(postID, userID))) {
            onLoading { sendEvent(PostDetailEvent.OnLoading) }
            onSuccess {  data ->
                if (data.error == "false") {
                    sendEvent(PostDetailEvent.OnSuccess)
                    setEffect(PostDetailSideEffect.Toast("게시글이 삭제 되었습니다."))
                    setEffect(PostDetailSideEffect.NavigatePopBackStack)
                }
                else {
                    sendEvent(PostDetailEvent.OnError)
                    setEffect(PostDetailSideEffect.Toast("게시글 작성자만 삭제 할 수 있습니다."))
                }
            }
            onError { sendEvent(PostDetailEvent.OnError) }
        }
    }

    fun uploadComment(postID: String, userID: String, comment: String) {
        viewModelScope.processApiResult(uploadCommentUseCase(Triple(postID, userID, comment))) {
            onLoading { sendEvent(PostDetailEvent.OnLoading) }
            onSuccess { data ->
                if (data.error == "false") sendEvent(PostDetailEvent.OnSuccess)
                else sendEvent(PostDetailEvent.OnError)
            }
            onError { sendEvent(PostDetailEvent.OnError) }
        }
    }
}