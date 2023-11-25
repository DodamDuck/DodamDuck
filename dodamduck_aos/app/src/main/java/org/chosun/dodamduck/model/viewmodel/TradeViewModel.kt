package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.dto.Trade
import org.chosun.dodamduck.model.repository.TradeRepository
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val repository: TradeRepository
) : BasePostViewModel<Trade>(repository) {

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
            repository.uploadTrade(userId, categoryId, title, content, location, image)
        }
    }
}
