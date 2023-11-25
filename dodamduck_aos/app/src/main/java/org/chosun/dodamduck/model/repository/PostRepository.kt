package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.dto.PostDetailDTO
import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.network.PostApiService
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val service: PostApiService?
) : BasePostRepository<PostDTO> {

    override suspend fun fetchList(): List<PostDTO> {
        return service?.getPostList() ?: listOf()
    }

    override suspend fun fetchDetail(
        shareID: String
    ): PostDetailResponse? {
        return service?.getPostDetail(shareID)
    }
}
