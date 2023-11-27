package org.chosun.dodamduck.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.repository.BasePostRepository

abstract class BasePostViewModel<ALL>(
    private val repository: BasePostRepository<ALL>
) : ViewModel() {

    private val _postLists = MutableStateFlow<List<ALL>?>(null)
    val postLists: StateFlow<List<ALL>?> = _postLists

    private val _postDetail = MutableStateFlow<PostDetailResponse?>(null)
    val postDetail: StateFlow<PostDetailResponse?> = _postDetail

    private val _deletePostError = MutableStateFlow<Boolean>(false)
    val deletePostError: StateFlow<Boolean> = _deletePostError

    fun fetchLists() {
        viewModelScope.launch {
            _postLists.value = repository.fetchList()
        }
    }

    fun fetchDetail(contentID: String) {
        viewModelScope.launch {
            _postDetail.value = repository.fetchDetail(contentID)
        }
    }

    fun updatePostLists(list: List<ALL>?) {
        _postLists.value = list
    }

    fun updatePostDetail(post: PostDetailResponse?) {
        _postDetail.value = post
    }

    fun uploadComment(postID: String, userID: String, comment: String) {
        viewModelScope.launch {
            val result = repository.uploadComment(postID, userID, comment)

            if (result?.error == "false") {
                fetchDetail(postID)
            }
        }
    }

    fun uploadViewCount(postID: String) {
        viewModelScope.launch {
            repository.uploadViewCount(postID)
        }
    }

    fun deletePost(
        postID: String,
        userID: String
    ) {
        viewModelScope.launch {
            val error = repository.deletePost(postID, userID)?.error
            _deletePostError.value = error == "true"
        }
    }
}
