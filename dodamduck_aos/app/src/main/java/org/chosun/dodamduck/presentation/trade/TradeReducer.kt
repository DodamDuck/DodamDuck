package org.chosun.dodamduck.presentation.trade

import org.chosun.dodamduck.presentation.base.Reducer

class TradeReducer(state: TradeState): Reducer<TradeState, TradeEvent>(state) {
    override fun reduce(oldState: TradeState, event: TradeEvent) {
        when(event) {
            is TradeEvent.OnSuccessTradeList -> setState(oldState.copy(isLoading = false, tradeList = event.tradeList))
            is TradeEvent.OnSuccess -> setState(oldState.copy(isLoading = false))
            is TradeEvent.OnError -> setState(oldState.copy(isLoading = false, isError = true))
            is TradeEvent.OnLoading -> setState(oldState.copy(isLoading = true, isError = false))
            is TradeEvent.OnSuccessTradeCategories -> setState(oldState.copy(isLoading = false, categories = event.categories))
            is TradeEvent.OnSuccessTradeDetail -> setState(oldState.copy(isLoading = false, tradeDetail = event.tradeDetail))
            is TradeEvent.OnSuccessSearchHistories -> setState(oldState.copy(isLoading = false, searchHistories = event.histories))
            is TradeEvent.OnSuccessSearchPopularHistories -> setState(oldState.copy(isLoading = false, popularSearchHistories = event.histories))
        }
    }
}