package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.PostDetailResponse

interface BasePostRepository<ALL> {
    suspend fun fetchList(): List<ALL>
    suspend fun fetchDetail(id: String): PostDetailResponse?
}