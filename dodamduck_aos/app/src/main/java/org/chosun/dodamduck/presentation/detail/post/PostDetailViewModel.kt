package org.chosun.dodamduck.presentation.detail.post

import dagger.hilt.android.lifecycle.HiltViewModel
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.domain.usecase.remote.post.CreateChat
import org.chosun.dodamduck.domain.usecase.remote.post.DeletePost
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostDetail
import org.chosun.dodamduck.domain.usecase.remote.post.UploadComment
import org.chosun.dodamduck.presentation.detail.base.BasePostDetailViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    getPostDetailUseCase: GetPostDetail<PostDto>,
    createChatUseCase: CreateChat<PostDto>,
    deletePostUseCase: DeletePost<PostDto>,
    uploadCommentUseCase: UploadComment<PostDto>,
) : BasePostDetailViewModel<PostDto>(
    getPostDetailUseCase,
    createChatUseCase,
    deletePostUseCase,
    uploadCommentUseCase,
)