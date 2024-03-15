package org.chosun.dodamduck.presentation.trade

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.database.SearchHistory
import org.chosun.dodamduck.data.dto.SearchDTO
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.usecase.remote.post.GetPostList
import org.chosun.dodamduck.domain.usecase.remote.post.SearchPost
import org.chosun.dodamduck.domain.usecase.remote.post.UploadPost
import org.chosun.dodamduck.domain.usecase.remote.post.UploadViewCount
import org.chosun.dodamduck.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val getTradeListUseCase: GetPostList<Trade>,
    private val uploadTradeUseCase: UploadPost<Trade>,
    private val searchTradeUseCase: SearchPost<Trade>,
    private val uploadViewCountUseCase: UploadViewCount<Trade>,
) : BaseViewModel<TradeState, TradeEvent, TradeSideEffect>(
    TradeReducer(TradeState.init())
) {

    private val _uploadSuccess = MutableStateFlow<Boolean>(false)
    val uploadSuccess: StateFlow<Boolean> = _uploadSuccess

    private val _searchQueryList = MutableStateFlow<List<SearchHistory>?>(null)
    val searchQueryList: StateFlow<List<SearchHistory>?> = _searchQueryList

    private val _popularSearchList = MutableStateFlow<List<SearchDTO>?>(null)
    val popularSearchList: StateFlow<List<SearchDTO>?> = _popularSearchList

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
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ) {
        viewModelScope.launch {
            sendEvent(TradeEvent.OnLoading)
            uploadTradeUseCase(userId, categoryId, title, content, location, image)
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
//            repository.insertSearchQuery(query)
            fetchSearchQuery()
        }
    }

    fun fetchSearchQuery() {
        viewModelScope.launch {
//            _searchQueryList.value = repository.getAllSearchHistory()
        }
    }

    fun deleteSearchQuery(query: String) {
        viewModelScope.launch {
//            repository.deleteSearchQuery(query)
            fetchSearchQuery()
        }
    }

    fun fetchPopularSearches() {
        viewModelScope.launch {
//            _popularSearchList.value = repository.fetchPopularSearch()
        }
    }

    fun deleteAllSearchQuery() {
        viewModelScope.launch {
//            repository.deleteAll()
            fetchSearchQuery()
        }
    }
}
