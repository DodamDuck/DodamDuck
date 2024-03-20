package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.post.PostUseCaseDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import org.chosun.dodamduck.network.response.DodamDuckResponse
import javax.inject.Inject

class CreateChat<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<PostUseCaseDto, DodamDuckResponse>() {

    override suspend fun execute(postUseCaseDto: PostUseCaseDto): Flow<ApiResult<DodamDuckResponse>> {
        return postRepo.createChat(postUseCaseDto.postId!!, postUseCaseDto.userId)
    }
}