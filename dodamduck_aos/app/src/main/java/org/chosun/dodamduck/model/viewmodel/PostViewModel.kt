package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.CategoryDTO
import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.repository.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : BasePostViewModel<PostDTO>(repository) {

    private val _categories = MutableStateFlow<List<CategoryDTO>?>(null)
    val categories: StateFlow<List<CategoryDTO>?> = _categories
    fun getPostLists() {
        viewModelScope.launch {
            updatePostLists(repository.fetchList())
        }
    }

    fun getPostLists(categoryID: String) {
        viewModelScope.launch {
            updatePostLists(repository.fetchList(categoryID))
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = repository.fetchCategories()
        }
    }
}