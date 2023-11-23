package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.network.PostApiService
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val service: PostApiService?
) {
    suspend fun fetchPostList(): List<PostDTO> {
        return service?.getPostList() ?: listOf()
    }
}