package org.chosun.dodamduck.domain.usecase.remote.trade

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.data.dto.search.SearchEntity
import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.TradeRepository
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class GetPopularSearch @Inject constructor(
    private val tradeRepo: TradeRepository<Trade>
): BaseResultUseCase<Nothing?, List<SearchEntity>>() {

    override suspend fun execute(params: Nothing?): Flow<ApiResult<List<SearchEntity>>> {
        return tradeRepo.fetchPopularSearch()
    }
}