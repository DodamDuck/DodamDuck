package org.chosun.dodamduck.presentation.trade

import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.data.dto.search.SearchEntity
import org.chosun.dodamduck.network.response.PostDetailResponse
import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.database.SearchHistory
import org.chosun.dodamduck.presentation.base.State

data class TradeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val categories: List<CategoryDto> = emptyList(),
    val tradeList: List<Trade> = emptyList(),
    val tradeDetail: PostDetailResponse? = null,
    val searchHistories: List<SearchHistory> = emptyList(),
    val popularSearchHistories: List<SearchEntity> = emptyList()
) : State {
    companion object {
        fun init() = TradeState()
    }
}