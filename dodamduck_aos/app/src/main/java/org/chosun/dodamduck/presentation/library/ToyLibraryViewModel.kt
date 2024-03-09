package org.chosun.dodamduck.presentation.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.ToyInfo
import org.chosun.dodamduck.model.repository.ToyLibraryRepository
import javax.inject.Inject

@HiltViewModel
class ToyLibraryViewModel @Inject constructor(
    private val repository: ToyLibraryRepository
): ViewModel() {

    private val _toyInfos = MutableStateFlow<List<ToyInfo>?>(null)
    val toyInfos: StateFlow<List<ToyInfo>?> = _toyInfos

    fun getToyInfos() {
        viewModelScope.launch {
            _toyInfos.value = repository.fetchToyList()
        }
    }
}