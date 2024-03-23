package org.chosun.dodamduck.presentation.trade

import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.data.dto.search.SearchEntity
import org.chosun.dodamduck.network.response.PostDetailResponse
import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.database.SearchHistory
import org.chosun.dodamduck.presentation.base.Event

sealed class TradeEvent: Event {
    data class OnSuccessTradeList(val tradeList: List<Trade>): TradeEvent()

    data class OnSuccessTradeCategories(val categories: List<CategoryDto>): TradeEvent()

    data class OnSuccessTradeDetail(val tradeDetail: PostDetailResponse): TradeEvent()

    data class OnSuccessSearchHistories(val histories: List<SearchHistory>): TradeEvent()

    data class OnSuccessSearchPopularHistories(val histories: List<SearchEntity>): TradeEvent()

    object OnSuccess: TradeEvent()

    object OnError: TradeEvent()

    object OnLoading: TradeEvent()
}