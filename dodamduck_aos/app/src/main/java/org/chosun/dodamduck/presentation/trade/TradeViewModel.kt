package org.chosun.dodamduck.presentation.trade

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.data.dto.post.PostUseCaseDto
import org.chosun.dodamduck.domain.usecase.local.trade.DeleteSearchQuery
import org.chosun.dodamduck.domain.usecase.local.trade.GetSearchHistories
import org.chosun.dodamduck.domain.usecase.local.trade.InsertSearchQuery
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostList
import org.chosun.dodamduck.domain.usecase.remote.post.SearchPost
import org.chosun.dodamduck.domain.usecase.remote.post.UploadPost
import org.chosun.dodamduck.domain.usecase.remote.post.UploadViewCount
import org.chosun.dodamduck.domain.usecase.remote.trade.GetPopularSearch
import org.chosun.dodamduck.presentation.base.BaseViewModel
import org.chosun.dodamduck.presentation.processApiResult
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val getTradeListUseCase: GetPostList<Trade>,
    private val uploadTradeUseCase: UploadPost<Trade>,
    private val searchTradeUseCase: SearchPost<Trade>,
    private val getPopularSearch: GetPopularSearch,
    private val uploadViewCountUseCase: UploadViewCount<Trade>,
    private val getSearchHistoriesUseCase: GetSearchHistories,
    private val insertSearchQueryUseCase: InsertSearchQuery,
    private val deleteSearchQueryUseCase: DeleteSearchQuery
) : BaseViewModel<TradeState, TradeEvent, TradeSideEffect>(
    TradeReducer(TradeState.init())
) {
    fun sendSideEffect(effect: TradeSideEffect) {
        setEffect(effect)
    }

    fun getTradeLists() {
        viewModelScope.processApiResult(getTradeListUseCase()) {
            onLoading { sendEvent(TradeEvent.OnLoading) }
            onSuccess { data -> sendEvent(TradeEvent.OnSuccessTradeList(data)) }
            onError { sendEvent(TradeEvent.OnError) }
        }
    }

    fun uploadTrade(
        userId: String,
        categoryId: String,
        title: String,
        content: String,
        location: String,
        image: MultipartBody.Part
    ) {
        viewModelScope.processApiResult(
            uploadTradeUseCase(
                PostUseCaseDto(
                    userId = userId,
                    categoryId = categoryId,
                    title = title,
                    content = content,
                    location = location,
                    image = image
                )
            ),
        ) {
            onLoading { sendEvent(TradeEvent.OnLoading) }
            onSuccess {
                sendEvent(TradeEvent.OnSuccess)
                setEffect(TradeSideEffect.NavigatePopBackStack)
            }
            onError { sendEvent(TradeEvent.OnError) }
        }
    }

    fun searchTrade(query: String) {
        viewModelScope.processApiResult(searchTradeUseCase(query)) {
            onLoading { sendEvent(TradeEvent.OnLoading) }
            onSuccess { data -> TradeEvent.OnSuccessTradeList(data) }
            onError { sendEvent(TradeEvent.OnError) }
        }
    }

    fun uploadViewCount(tradeId: String) {
        viewModelScope.processApiResult(uploadViewCountUseCase(tradeId)) {
            onLoading { sendEvent(TradeEvent.OnLoading) }
            onSuccess { sendEvent(TradeEvent.OnSuccess) }
            onError { sendEvent(TradeEvent.OnError) }
        }
    }

    fun insertSearchQuery(query: String) {
        viewModelScope.launch {
            sendEvent(TradeEvent.OnLoading)
            insertSearchQueryUseCase(query)
            fetchSearchQuery()
        }
    }

    fun fetchSearchQuery() {
        viewModelScope.processApiResult(getSearchHistoriesUseCase(null)) {
            onLoading { sendEvent(TradeEvent.OnLoading) }
            onSuccess { data -> sendEvent(TradeEvent.OnSuccessSearchHistories(data)) }
            onError { sendEvent(TradeEvent.OnError) }
        }
    }

    fun deleteSearchQuery(query: String) {
        viewModelScope.launch {
            deleteSearchQueryUseCase(query)
            fetchSearchQuery()
        }
    }

    fun fetchPopularSearches() {
        viewModelScope.processApiResult(getPopularSearch(null)) {
            onLoading { sendEvent(TradeEvent.OnLoading) }
            onSuccess { data -> sendEvent(TradeEvent.OnSuccessSearchPopularHistories(data)) }
            onError { sendEvent(TradeEvent.OnError) }
        }
    }

    fun deleteAllSearchQuery() {
        viewModelScope.launch {
            deleteSearchQueryUseCase()
            fetchSearchQuery()
        }
    }
}
