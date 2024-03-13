package org.chosun.dodamduck.data.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.data.safeFlow
import org.chosun.dodamduck.data.source.local.PostLocalSource
import org.chosun.dodamduck.data.source.remote.PostRemoteSource
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.PostRepository
import org.chosun.dodamduck.network.DodamDuckResponse
import javax.inject.Inject

class PostRepositoryImpl<T> @Inject constructor(
    private val postRemoteSource: PostRemoteSource,
    private val postLocalSource: PostLocalSource
) : PostRepository<PostDTO> {

    override suspend fun uploadPost(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ): Flow<ApiResult<Boolean>> = safeFlow {
        postRemoteSource.uploadPost(userId, categoryId, title, content, location, image)
    }

    override suspend fun fetchList(): Flow<ApiResult<List<PostDTO>>> = safeFlow {
        postRemoteSource.fetchList() ?: emptyList()
    }

    override suspend fun fetchList(categoryID: String): Flow<ApiResult<List<PostDTO>>> = safeFlow {
        postRemoteSource.fetchList(categoryID)
    }

    override suspend fun fetchDetail(
        id: String
    ): Flow<ApiResult<PostDetailResponse?>> = safeFlow {
        postRemoteSource.fetchDetail(id)
    }

    override suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ): Flow<ApiResult<DodamDuckResponse?>> = safeFlow {
        postRemoteSource.uploadComment(postID, userID, comment)
    }

    override suspend fun uploadViewCount(
        postID: String
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        postRemoteSource.uploadViewCount(postID)
    }

    override suspend fun deletePost(
        postID: String,
        userID: String
    ): Flow<ApiResult<DodamDuckResponse?>> = safeFlow {
        postRemoteSource.deletePost(postID, userID)
    }

    override suspend fun createChat(
        postID: String,
        userID: String
    ): Flow<ApiResult<DodamDuckResponse>> = safeFlow {
        postRemoteSource.createChat(postID, userID)
    }

    override suspend fun searchPost(
        query: String
    ): Flow<ApiResult<List<PostDTO>>> = safeFlow {
        postRemoteSource.searchPost(query)
    }

    override suspend fun fetchCategories(): Flow<ApiResult<List<CategoryDTO>>> = safeFlow {
        postRemoteSource.fetchCategories()
    }

//    suspend fun getAllSearchHistory(): List<SearchHistory> {
//        return searchHistoryDao.getAllSearchHistory()
//    }
//
//    suspend fun insertSearchQuery(searchHistory: SearchHistory) {
//        searchHistoryDao.insertSearchQuery(searchHistory)
//    }
//
//    suspend fun deleteAll() {
//        searchHistoryDao.deleteAll()
//    }
}
