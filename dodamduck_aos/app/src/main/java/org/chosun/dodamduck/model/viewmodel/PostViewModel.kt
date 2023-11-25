package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.repository.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : BasePostViewModel<PostDTO>(repository) {
    fun getPostLists() {
        viewModelScope.launch {
            updatePostLists(repository.fetchList())
        }
    }
}