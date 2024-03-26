package org.chosun.dodamduck.data.dto.auth

import com.google.gson.annotations.SerializedName

data class LoginDto(
    val userID: String,
    val userName: String,
    val location: String,
    @SerializedName("verification_count")
    val verificationCount: String,
    val level: String,
    @SerializedName("profile_url")
    val profileUrl: String,
    @SerializedName("login_success")
    val loginSuccess: Boolean,
    val token: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)