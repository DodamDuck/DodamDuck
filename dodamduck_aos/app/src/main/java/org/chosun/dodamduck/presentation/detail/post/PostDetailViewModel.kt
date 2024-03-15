package org.chosun.dodamduck.presentation.detail.post

import dagger.hilt.android.lifecycle.HiltViewModel
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.domain.usecase.remote.post.CreateChat
import org.chosun.dodamduck.domain.usecase.remote.post.DeletePost
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostDetail
import org.chosun.dodamduck.domain.usecase.remote.post.UploadComment
import org.chosun.dodamduck.presentation.detail.base.BasePostDetailViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    getPostDetailUseCase: GetPostDetail<PostDTO>,
    createChatUseCase: CreateChat<PostDTO>,
    deletePostUseCase: DeletePost<PostDTO>,
    uploadCommentUseCase: UploadComment<PostDTO>,
) : BasePostDetailViewModel<PostDTO>(
    getPostDetailUseCase,
    createChatUseCase,
    deletePostUseCase,
    uploadCommentUseCase,
)