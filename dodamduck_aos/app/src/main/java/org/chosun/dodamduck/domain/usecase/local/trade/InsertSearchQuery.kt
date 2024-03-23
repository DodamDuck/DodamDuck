package org.chosun.dodamduck.domain.usecase.local.trade

import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.domain.repository.TradeRepository
import javax.inject.Inject

class InsertSearchQuery @Inject constructor(
    private val tradeRepo: TradeRepository<Trade>
) {
    suspend operator fun invoke(query: String) {
        tradeRepo.insertSearchQuery(query)
    }
}