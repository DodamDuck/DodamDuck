package org.chosun.dodamduck.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.database.SearchHistory
import org.chosun.dodamduck.data.database.SearchHistoryDao
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.data.dto.SearchDTO
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.network.DodamDuckResponse
import org.chosun.dodamduck.network.TradeApiService
import javax.inject.Inject

class TradeRepository @Inject constructor(
    private val service: TradeApiService?,
    private val searchHistoryDao: SearchHistoryDao
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
    ):Boolean {
        val response = service?.uploadTrade(userId, categoryId, title, content, location, image)

        return response?.error == "false"
    }

    override suspend fun fetchDetail(
        postId: String
    ): PostDetailResponse? {
        return service?.getTradeDetail(postId)
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
        return service?.deleteTrade(postID, userID)
    }

    override suspend fun createChat(postID: String, userID: String): DodamDuckResponse? {
        return service?.createChat(postID, userID)
    }

    override suspend fun searchPost(query: String): List<Trade> {
        return service?.searchPost(query) ?: listOf()
    }

    suspend fun getAllSearchHistory(): List<SearchHistory> {
        return searchHistoryDao.getAllSearchHistory()
    }

    suspend fun insertSearchQuery(query: String) {
        searchHistoryDao.insertSearchQuery(SearchHistory(query = query))
    }

    suspend fun deleteAll() {
        searchHistoryDao.deleteAll()
    }

    suspend fun deleteSearchQuery(query: String) {
        searchHistoryDao.deleteSearchQuery(query)
    }

    suspend fun fetchPopularSearch(): List<SearchDTO> {
        return service?.popularSearch() ?: listOf()
    }
}