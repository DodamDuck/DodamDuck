package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.Trade
import org.chosun.dodamduck.model.network.TradeApiService
import javax.inject.Inject

class TradeRepository @Inject constructor(
    private val service: TradeApiService?
) {
    suspend fun fetchTradeList(): List<Trade> {
        return service?.getTradeList() ?: listOf()
    }
}