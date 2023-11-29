package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.network.DodamDuckResponse

interface BasePostRepository<ALL> {
    suspend fun fetchList(): List<ALL>

    suspend fun fetchDetail(id: String): PostDetailResponse?

    suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ): DodamDuckResponse?

    suspend fun uploadViewCount(
        postID: String
    ): DodamDuckResponse?

    suspend fun deletePost(
        postID: String,
        userID: String
    ): DodamDuckResponse?

    suspend fun createChat(
        postID: String,
        userID: String
    ): DodamDuckResponse?
}