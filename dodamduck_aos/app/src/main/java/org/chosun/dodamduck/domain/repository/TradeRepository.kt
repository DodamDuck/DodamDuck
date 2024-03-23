package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.search.SearchEntity
import org.chosun.dodamduck.domain.model.ApiResult

interface TradeRepository <ALL>: BasePostRepository<ALL> {

    suspend fun fetchPopularSearch(): Flow<ApiResult<List<SearchEntity>>>
}