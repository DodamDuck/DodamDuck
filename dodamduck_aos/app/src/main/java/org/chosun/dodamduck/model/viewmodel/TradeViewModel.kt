package org.chosun.dodamduck.model.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.Trade
import org.chosun.dodamduck.model.repository.TradeRepository
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val repository: TradeRepository
): ViewModel() {

    private val _tradeLists = MutableStateFlow<List<Trade>?>(null)
    val tradeLists: StateFlow<List<Trade>?> = _tradeLists

    fun getTradeLists() {
        viewModelScope.launch {
            _tradeLists.value = repository.fetchTradeList()
        }
    }
}