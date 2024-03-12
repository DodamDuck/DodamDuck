package org.chosun.dodamduck.network

import org.chosun.dodamduck.data.dto.LoginDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {

    @FormUrlEncoded
    @POST("Login.php")
    suspend fun requestLogin(
        @Field("userID") userID: String,
        @Field("userPassword") password: String
    ): LoginDTO

    @FormUrlEncoded
    @POST("Register.php")
    suspend fun requestRegister(
        @Field("userID") userID: String,
        @Field("userPassword") password: String
    ): DodamDuckResponse

}