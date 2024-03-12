package org.chosun.dodamduck.data.source.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.chosun.dodamduck.network.PostApiService
import javax.inject.Inject

class PostRemoteSource @Inject constructor(
    private val postApiService: PostApiService
) {
    suspend fun uploadPost(
        userId: RequestBody,
        categoryId: RequestBody,
        title: RequestBody,
        content: RequestBody,
        location: RequestBody,
        image: MultipartBody.Part
    ) = postApiService
        .uploadPost(userId, categoryId, title, content, location, image)
        .error == "false"

    suspend fun fetchList() = postApiService.getPostList()

    suspend fun fetchList(categoryID: String) = postApiService.getPostList(categoryID)

    suspend fun fetchDetail(id: String) = postApiService.getPostDetail(id)

    suspend fun uploadComment(
        postID: String,
        userID: String,
        comment: String
    ) = postApiService.uploadComment(postID, userID, comment)

    suspend fun uploadViewCount(postID: String) = postApiService.uploadViews(postID)


    suspend fun deletePost(postID: String, userID: String) =
        postApiService.deletePost(postID, userID)

    suspend fun createChat(postID: String, userID: String) =
        postApiService.createChat(postID, userID)


    suspend fun searchPost(query: String) = postApiService.searchPost(query)

    suspend fun fetchCategories() = postApiService.getCategories()

}