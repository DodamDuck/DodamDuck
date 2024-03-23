package org.chosun.dodamduck.data.source.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.network.service.TradeApiService
import javax.inject.Inject

class TradeRemoteSource @Inject constructor(
    private val tradeApiService: TradeApiService
) {
    suspend fun fetchList() = tradeApiService.getTradeList()

    suspend fun uploadTrade(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ) = tradeApiService.uploadTrade(userId, categoryId, title, content, location, image).error == "false"

    suspend fun fetchDetail(
        postId: String
    ) = tradeApiService.getTradeDetail(postId)

    suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ) = tradeApiService.uploadComment(postID, userID, comment)

    suspend fun uploadViewCount(postID: String) = tradeApiService.uploadViews(postID)

    suspend fun deleteTrade(postID: String, userID: String) =
        tradeApiService.deleteTrade(postID, userID)

    suspend fun createChat(postID: String, userID: String) =
        tradeApiService.createChat(postID, userID)


    suspend fun searchTrade(query: String) = tradeApiService.searchPost(query)

    suspend fun fetchPopularSearch() = tradeApiService.popularSearch()

}