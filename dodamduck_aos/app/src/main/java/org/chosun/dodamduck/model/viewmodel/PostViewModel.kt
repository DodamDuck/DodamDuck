package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.repository.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _postLists = MutableStateFlow<List<PostDTO>?>(null)
    val postLists: StateFlow<List<PostDTO>?> = _postLists

    fun getPostLists() {
        viewModelScope.launch {
            _postLists.value = repository.fetchPostList()
        }
    }
}