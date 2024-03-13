package org.chosun.dodamduck.presentation.trade

import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.presentation.base.State

data class TradeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val categories: List<CategoryDTO> = emptyList(),
    val tradeList: List<Trade> = emptyList(),
    val tradeDetail: PostDetailResponse? = null
): State {
    companion object {
        fun init() = TradeState()
    }
}