package org.chosun.dodamduck.network.auth

interface TokenRefresher {
    suspend fun refreshAccessToken(refreshToken: String): String
}