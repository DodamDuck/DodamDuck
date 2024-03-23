package org.chosun.dodamduck.presentation.trade

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.chosun.dodamduck.data.dto.trade.Trade
import org.chosun.dodamduck.data.dto.post.PostUseCaseDto
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.local.trade.DeleteSearchQuery
import org.chosun.dodamduck.domain.usecase.local.trade.GetSearchHistories
import org.chosun.dodamduck.domain.usecase.local.trade.InsertSearchQuery
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostList
import org.chosun.dodamduck.domain.usecase.remote.post.SearchPost
import org.chosun.dodamduck.domain.usecase.remote.post.UploadPost
import org.chosun.dodamduck.domain.usecase.remote.post.UploadViewCount
import org.chosun.dodamduck.domain.usecase.remote.trade.GetPopularSearch
import org.chosun.dodamduck.presentation.base.BaseViewModel
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
        viewModelScope.launch {
            sendEvent(TradeEvent.OnLoading)
            getTradeListUseCase().collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        sendEvent(TradeEvent.OnSuccessTradeList(apiResult.value))
                    }

                    is ApiResult.Error -> sendEvent(TradeEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
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
        viewModelScope.launch {
            sendEvent(TradeEvent.OnLoading)
            uploadTradeUseCase(
                PostUseCaseDto(
                    userId = userId,
                    categoryId = categoryId,
                    title = title,
                    content = content,
                    location = location,
                    image = image
                )
            )
                .collectLatest { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success -> {
                            sendEvent(TradeEvent.OnSuccess)
                            setEffect(TradeSideEffect.NavigatePopBackStack)
                        }

                        is ApiResult.Error -> sendEvent(TradeEvent.OnError)

                        is ApiResult.Exception -> {}
                    }
                }
        }
    }

    fun searchTrade(query: String) {
        viewModelScope.launch {
            sendEvent(TradeEvent.OnLoading)
            searchTradeUseCase(query)
                .collectLatest { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success -> {
                            sendEvent(TradeEvent.OnSuccessTradeList(apiResult.value))
                        }

                        is ApiResult.Error -> sendEvent(TradeEvent.OnError)

                        is ApiResult.Exception -> {}
                    }
                }
        }
    }

    fun uploadViewCount(tradeId: String) {
        viewModelScope.launch {
            sendEvent(TradeEvent.OnLoading)
            uploadViewCountUseCase(tradeId)
                .collectLatest { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success -> {
                            sendEvent(TradeEvent.OnSuccess)
                        }

                        is ApiResult.Error -> sendEvent(TradeEvent.OnError)

                        is ApiResult.Exception -> {}
                    }
                }
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
        viewModelScope.launch {
            sendEvent(TradeEvent.OnLoading)
            getSearchHistoriesUseCase(null).collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        sendEvent(TradeEvent.OnSuccessSearchHistories(apiResult.value))
                    }

                    is ApiResult.Error -> sendEvent(TradeEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun deleteSearchQuery(query: String) {
        viewModelScope.launch {
            deleteSearchQueryUseCase(query)
            fetchSearchQuery()
        }
    }

    fun fetchPopularSearches() {
        viewModelScope.launch {
            getPopularSearch(null).collectLatest {apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        sendEvent(TradeEvent.OnSuccessSearchPopularHistories(apiResult.value))
                    }

                    is ApiResult.Error -> sendEvent(TradeEvent.OnError)

                    is ApiResult.Exception -> {}
                }
            }
        }
    }

    fun deleteAllSearchQuery() {
        viewModelScope.launch {
            deleteSearchQueryUseCase()
            fetchSearchQuery()
        }
    }
}
