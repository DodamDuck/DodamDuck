package org.chosun.dodamduck.domain.usecase.local.trade

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.database.SearchHistory
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.TradeRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class GetSearchHistories @Inject constructor(
    private val tradeRepo: TradeRepository<Trade>
): BaseResultUseCase<Nothing?, List<SearchHistory>>() {
    override suspend fun execute(params: Nothing?): Flow<ApiResult<List<SearchHistory>>> {
        return tradeRepo.getSearchHistories()
    }

}