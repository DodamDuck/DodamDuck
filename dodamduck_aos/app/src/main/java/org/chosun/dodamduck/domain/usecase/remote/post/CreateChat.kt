package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.data.dto.auth.AuthDto
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class CreateChat<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<PostDto, DodamDuckResponse>() {

    override suspend fun execute(postDto: PostDto): Flow<ApiResult<DodamDuckResponse>> {
        return postRepo.createChat(postDto.postID, postDto.userID)
    }
}