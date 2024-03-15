package org.chosun.dodamduck.presentation.trade

import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.presentation.base.Event

sealed class TradeEvent: Event {
    data class OnSuccessTradeList(val tradeList: List<Trade>): TradeEvent()

    data class OnSuccessTradeCategories(val categories: List<CategoryDTO>): TradeEvent()

    data class OnSuccessTradeDetail(val tradeDetail: PostDetailResponse): TradeEvent()

    object OnSuccess: TradeEvent()

    object OnError: TradeEvent()

    object OnLoading: TradeEvent()
}