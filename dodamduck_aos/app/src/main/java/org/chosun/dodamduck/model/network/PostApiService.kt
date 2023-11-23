package org.chosun.dodamduck.model.network

import org.chosun.dodamduck.model.dto.PostDTO
import retrofit2.http.GET

interface PostApiService {

    @GET("ContentShare.php")
    suspend fun getPostList(): List<PostDTO>?
}