package org.chosun.dodamduck.presentation.trade

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.database.SearchHistory
import org.chosun.dodamduck.data.dto.SearchDTO
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.data.repository.TradeRepository
import org.chosun.dodamduck.presentation.base.BasePostViewModel
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val repository: TradeRepository
) : BasePostViewModel<Trade>(repository) {

    private val _uploadSuccess = MutableStateFlow<Boolean>(false)
    val uploadSuccess: StateFlow<Boolean> = _uploadSuccess

    private val _searchQueryList = MutableStateFlow<List<SearchHistory>?>(null)
    val searchQueryList: StateFlow<List<SearchHistory>?> = _searchQueryList

    private val _popularSearchList = MutableStateFlow<List<SearchDTO>?>(null)
    val popularSearchList: StateFlow<List<SearchDTO>?> = _popularSearchList

    fun getTradeLists() {
        viewModelScope.launch {
            updatePostLists(repository.fetchList())
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
            val isSuccess = repository.uploadTrade(userId, categoryId, title, content, location, image)
            _uploadSuccess.value = isSuccess
        }
    }

    fun insertSearchQuery(query: String) {
        viewModelScope.launch {
            repository.insertSearchQuery(query)
            fetchSearchQuery()
        }
    }

    fun fetchSearchQuery() {
        viewModelScope.launch {
            _searchQueryList.value = repository.getAllSearchHistory()
        }
    }

    fun deleteSearchQuery(query: String) {
        viewModelScope.launch {
            repository.deleteSearchQuery(query)
            fetchSearchQuery()
        }
    }

    fun fetchPopularSearches() {
        viewModelScope.launch {
            _popularSearchList.value = repository.fetchPopularSearch()
        }
    }

    fun deleteAllSearchQuery() {
        viewModelScope.launch {
            repository.deleteAll()
            fetchSearchQuery()
        }
    }
}
