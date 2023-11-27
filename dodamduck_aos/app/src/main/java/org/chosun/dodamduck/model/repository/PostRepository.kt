package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.CategoryDTO
import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.network.DodamDuckResponse
import org.chosun.dodamduck.model.network.PostApiService
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val service: PostApiService?
) : BasePostRepository<PostDTO> {

    override suspend fun fetchList(): List<PostDTO> {
        return service?.getPostList() ?: listOf()
    }

    suspend fun fetchList(categoryID: String): List<PostDTO> {
        return service?.getPostList(categoryID) ?: listOf()
    }

    override suspend fun fetchDetail(
        shareID: String
    ): PostDetailResponse? {
        return service?.getPostDetail(shareID)
    }

    override suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ): DodamDuckResponse? {
        return service?.uploadComment(postID, userID, comment)
    }

    override suspend fun uploadViewCount(postID: String): DodamDuckResponse? {
        return service?.uploadViews(postID)
    }

    override suspend fun deletePost(postID: String, userID: String): DodamDuckResponse? {
        return service?.deletePost(postID, userID)
    }

    suspend fun fetchCategories(): List<CategoryDTO>? {
        return service?.getCategories()
    }
}
