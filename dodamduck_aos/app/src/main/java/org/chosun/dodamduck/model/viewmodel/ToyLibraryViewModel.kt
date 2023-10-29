package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.ToyInfo
import org.chosun.dodamduck.model.repository.ToyLibraryRepository
import javax.inject.Inject

@HiltViewModel
class ToyLibraryViewModel @Inject constructor(
    private val repository: ToyLibraryRepository
): ViewModel() {

    private val _toyInfos = MutableLiveData<List<ToyInfo>>()
    val toyInfos: LiveData<List<ToyInfo>> = _toyInfos

    fun getToyInfos() {
        viewModelScope.launch {
            _toyInfos.value = repository.fetchToyList()
        }
    }
}