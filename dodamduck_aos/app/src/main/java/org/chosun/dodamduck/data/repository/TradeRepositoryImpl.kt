package org.chosun.dodamduck.data.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.chosun.dodamduck.database.SearchHistory
import org.chosun.dodamduck.network.response.PostDetailResponse
import org.chosun.dodamduck.data.dto.search.SearchEntity
import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.data.model.DodamDuckData
import org.chosun.dodamduck.data.safeFlow
import org.chosun.dodamduck.data.source.local.TradeLocalSource
import org.chosun.dodamduck.data.source.remote.TradeRemoteSource
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.TradeRepository
import org.chosun.dodamduck.network.response.DodamDuckResponse
import javax.inject.Inject

/**
 * The generic in this interface is intended for injecting a specific implementation of the BasePostRepository
 */

class TradeRepositoryImpl<T> @Inject constructor(
    private val tradeRemoteSource: TradeRemoteSource,
    private val tradeLocalSource: TradeLocalSource
) : TradeRepository<Trade> {

    override suspend fun fetchList(): Flow<ApiResult<List<Trade>>> = safeFlow {
        tradeRemoteSource.fetchList() ?: emptyList()
    }

    override suspend fun uploadPost(
        userId: String,
        categoryId: String,
        title: String,
        content: String,
        location: String,
        image: MultipartBody.Part
    ):  Flow<ApiResult<Boolean>> = safeFlow {
        val userIdBody = DodamDuckData.userInfo.userID.toRequestBody("text/plain".toMediaTypeOrNull())
        val categoryIdBody = categoryId.toRequestBody("text/plain".toMediaTypeOrNull())
        val titleBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val contentBody = content.toRequestBody("text/plain".toMediaTypeOrNull())
        val locationBody = location.toRequestBody("text/plain".toMediaTypeOrNull())

        tradeRemoteSource.uploadTrade(userIdBody, categoryIdBody, titleBody, contentBody, locationBody, image)
    }

    override suspend fun fetchDetail(
        id: String
    ): Flow<ApiResult<PostDetailResponse>> = safeFlow {
        tradeRemoteSource.fetchDetail(id)
    }

    override suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        tradeRemoteSource.uploadComment(postID, userID, comment)
    }

    override suspend fun uploadViewCount(
        postID: String
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        tradeRemoteSource.uploadViewCount(postID)
    }

    override suspend fun deletePost(
        postID: String, userID: String
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
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

    override suspend fun getSearchHistories(): Flow<ApiResult<List<SearchHistory>>> = safeFlow {
        tradeLocalSource.getAllSearchHistory()
    }

    override suspend fun fetchPopularSearch(): Flow<ApiResult<List<SearchEntity>>> = safeFlow {
        tradeRemoteSource.fetchPopularSearch()
    }


    override suspend fun insertSearchQuery(query: String) {
        tradeLocalSource.insertSearchQuery(SearchHistory(query = query))
    }

    override suspend fun deleteSearchQuery(query: String) {
        tradeLocalSource.deleteSearchQuery(query)
    }

    override suspend fun deleteAllSearchQuery() {
        tradeLocalSource.deleteAll()
    }
}