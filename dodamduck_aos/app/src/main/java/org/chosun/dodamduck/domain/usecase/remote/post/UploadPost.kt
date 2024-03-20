package org.chosun.dodamduck.domain.usecase.remote.post

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class UploadPost<T> @Inject constructor(
    private val postRepo: BasePostRepository<T>
): BaseResultUseCase<PostDto, Boolean>() {

    override suspend fun execute(params: PostDto): Flow<ApiResult<Boolean>> {
        return postRepo.uploadPost(
            userId = params.userId,
            categoryId = params.categoryId!!,
            title = params.title!!,
            content = params.content!!,
            location = params.location!!,
            image = params.image!!
        )
    }
}