package org.chosun.dodamduck.network.service

import org.chosun.dodamduck.data.dto.auth.LoginDto
import org.chosun.dodamduck.network.response.DodamDuckResponse
import org.chosun.dodamduck.network.response.JwtResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {

    @FormUrlEncoded
    @POST("Login.php")
    suspend fun requestLogin(
        @Field("userID") userID: String,
        @Field("userPassword") password: String
    ): LoginDto

    @FormUrlEncoded
    @POST("Register.php")
    suspend fun requestRegister(
        @Field("userID") userID: String,
        @Field("userPassword") password: String
    ): DodamDuckResponse

    @FormUrlEncoded
    @POST("AccessToken.php")
    suspend fun refreshToken(
        @Field("refresh_token") refreshToken: String
    ): JwtResponse
}