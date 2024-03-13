package org.chosun.dodamduck.data.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.database.SearchHistory
import org.chosun.dodamduck.data.database.SearchHistoryDao
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.data.dto.SearchDTO
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.data.safeFlow
import org.chosun.dodamduck.data.source.remote.TradeRemoteSource
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.TradeRepository
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class TradeRepositoryImpl<T> @Inject constructor(
    private val tradeRemoteSource: TradeRemoteSource,
    private val searchHistoryDao: SearchHistoryDao
) : TradeRepository<Trade> {

    override suspend fun fetchList(): Flow<ApiResult<List<Trade>>> = safeFlow {
        tradeRemoteSource.fetchList() ?: emptyList()
    }

    override suspend fun uploadPost(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ):  Flow<ApiResult<Boolean>> = safeFlow {
        tradeRemoteSource.uploadTrade(userId, categoryId, title, content, location, image)
    }

    override suspend fun fetchDetail(
        id: String
    ): Flow<ApiResult<PostDetailResponse?>> = safeFlow {
        tradeRemoteSource.fetchDetail(id)
    }

    override suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ): Flow<ApiResult<DodamDuckResponse?>> = safeFlow {
        tradeRemoteSource.uploadComment(postID, userID, comment)
    }

    override suspend fun uploadViewCount(
        postID: String
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        tradeRemoteSource.uploadViewCount(postID)
    }

    override suspend fun deletePost(
        postID: String, userID: String
    ): Flow<ApiResult<DodamDuckResponse?>> = safeFlow {
        tradeRemoteSource.deleteTrade(postID, userID)
    }

    override suspend fun createChat(
        postID: String,
        userID: String
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        tradeRemoteSource.createChat(postID, userID)
    }

    override suspend fun searchPost(
        query: String
    ): Flow<ApiResult<List<Trade>>> = safeFlow {
        tradeRemoteSource.searchTrade(query)
    }

    suspend fun fetchPopularSearch(): Flow<ApiResult<List<SearchDTO>>> = safeFlow {
        tradeRemoteSource.fetchPopularSearch()
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
}