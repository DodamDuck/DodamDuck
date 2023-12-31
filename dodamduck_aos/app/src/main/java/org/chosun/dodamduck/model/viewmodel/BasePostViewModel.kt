package org.chosun.dodamduck.model.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
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

    fun searchPost(query: String) {
        viewModelScope.launch {
            _postLists.value = repository.searchPost(query)
        }
    }

    suspend fun deletePost(
        postID: String,
        userID: String
    ): Boolean {
        val result = viewModelScope.async() {
            val error = repository.deletePost(postID, userID)?.error
            return@async error == "false"
        }
        return result.await()
    }

    suspend fun createChat(
        postID: String,
        userID: String
    ): Boolean {
        val result = viewModelScope.async() {
            val error = repository.createChat(postID, userID)?.error
            return@async error == "false"
        }
        return result.await()
    }
}
