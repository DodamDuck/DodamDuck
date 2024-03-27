package org.chosun.dodamduck.network.response

import com.google.gson.annotations.SerializedName

data class JwtResponse(
    @SerializedName("access_token")
    val accessToken: String
)