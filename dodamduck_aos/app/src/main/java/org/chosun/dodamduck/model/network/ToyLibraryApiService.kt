package org.chosun.dodamduck.model.network

import org.chosun.dodamduck.BuildConfig
import org.chosun.dodamduck.model.dto.ToyInfo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ToyLibraryApiService {
    @GET("15044149/v1/uddi:a5346e5a-3cc9-4d96-a2e2-897d4ac79299_201905301139")
    @Headers("Authorization: Infuser ${BuildConfig.API_KEY}")
    suspend fun getToyList(
        @Query("page") page: Int? = 0,
        @Query("perPage") perPage: Int = 0
    ): OpenApiResponse<List<ToyInfo>>
}