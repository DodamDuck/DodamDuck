package org.chosun.dodamduck.model.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.model.database.SearchHistory
import org.chosun.dodamduck.model.database.SearchHistoryDao
import org.chosun.dodamduck.model.dto.CategoryDTO
import org.chosun.dodamduck.model.dto.PostDTO
import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.network.DodamDuckResponse
import org.chosun.dodamduck.model.network.PostApiService
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val service: PostApiService?,
    private val searchHistoryDao: SearchHistoryDao
) : BasePostRepository<PostDTO> {

    suspend fun uploadPost(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ):Boolean {
        val response = service?.uploadPost(userId, categoryId, title, content, location, image)

        return response?.error == "false"
    }
    override suspend fun fetchList(): List<PostDTO> {
        return service?.getPostList() ?: listOf()
    }

    suspend fun fetchList(categoryID: String): List<PostDTO> {
        return service?.getPostList(categoryID) ?: listOf()
    }

    override suspend fun fetchDetail(
        id: String
    ): PostDetailResponse? {
        return service?.getPostDetail(id)
    }

    override suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ): DodamDuckResponse? {
        return service?.uploadComment(postID, userID, comment)
    }

    override suspend fun uploadViewCount(postID: String): DodamDuckResponse? {
        return service?.uploadViews(postID)
    }

    override suspend fun deletePost(postID: String, userID: String): DodamDuckResponse? {
        return service?.deletePost(postID, userID)
    }

    override suspend fun createChat(postID: String, userID: String): DodamDuckResponse? {
        return service?.createChat(postID, userID)
    }

    override suspend fun searchPost(query: String): List<PostDTO> {
        return service?.searchPost(query) ?: listOf()
    }

    suspend fun fetchCategories(): List<CategoryDTO>? {
        return service?.getCategories()
    }

    suspend fun getAllSearchHistory(): List<SearchHistory> {
        return searchHistoryDao.getAllSearchHistory()
    }

    suspend fun insertSearchQuery(searchHistory: SearchHistory) {
        searchHistoryDao.insertSearchQuery(searchHistory)
    }

    suspend fun deleteAll() {
        searchHistoryDao.deleteAll()
    }
}
