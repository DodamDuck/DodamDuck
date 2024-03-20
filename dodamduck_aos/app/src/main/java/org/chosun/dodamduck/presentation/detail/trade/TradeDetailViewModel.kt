package org.chosun.dodamduck.presentation.detail.trade

import dagger.hilt.android.lifecycle.HiltViewModel
import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.domain.usecase.remote.post.CreateChat
import org.chosun.dodamduck.domain.usecase.remote.post.DeletePost
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostDetail
import org.chosun.dodamduck.domain.usecase.remote.post.UploadComment
import org.chosun.dodamduck.presentation.detail.base.BasePostDetailViewModel
import javax.inject.Inject

@HiltViewModel
class TradeDetailViewModel  @Inject constructor(
    getPostDetailUseCase: GetPostDetail<Trade>,
    createChatUseCase: CreateChat<Trade>,
    deletePostUseCase: DeletePost<Trade>,
    uploadCommentUseCase: UploadComment<Trade>,
) : BasePostDetailViewModel<Trade>(
    getPostDetailUseCase,
    createChatUseCase,
    deletePostUseCase,
    uploadCommentUseCase,
)