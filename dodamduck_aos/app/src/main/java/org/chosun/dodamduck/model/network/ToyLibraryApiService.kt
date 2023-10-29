package org.chosun.dodamduck.model.network

import org.chosun.dodamduck.model.dto.ToyInfo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ToyLibraryApiService {
    @GET("15044146/v1/uddi:7894ac31-fe17-420a-834a-824c42470e0e_201905301141")
    @Headers("Authorization: key")
    suspend fun getToyList(
        @Query("page") page: Int? = 1,
        @Query("perPage") perPage: Int = 10,
        @Query("returnType") returnType: String = "JSON",
    ): OpenApiResponse<List<ToyInfo>>
}