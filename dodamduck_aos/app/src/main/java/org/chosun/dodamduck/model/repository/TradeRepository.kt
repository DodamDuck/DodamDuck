package org.chosun.dodamduck.model.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.dto.Trade
import org.chosun.dodamduck.model.network.TradeApiService
import javax.inject.Inject

class TradeRepository @Inject constructor(
    private val service: TradeApiService?
):  BasePostRepository<Trade>  {
    override suspend fun fetchList(): List<Trade> {
        return service?.getTradeList() ?: listOf()
    }

    suspend fun uploadTrade(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ) {
        service?.uploadTrade(userId, categoryId, title, content, location, image)
    }

    override suspend fun fetchDetail(
        postId: String
    ): PostDetailResponse? {
        return service?.getTradeDetail(postId)
    }
}