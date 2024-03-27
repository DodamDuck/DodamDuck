package org.chosun.dodamduck.network.auth

import org.chosun.dodamduck.network.service.AuthApiService
import javax.inject.Inject
import javax.inject.Provider

class AuthTokenRefresher @Inject constructor(
    private val authApiServiceProvider: Provider<AuthApiService>
): TokenRefresher {
    override suspend fun refreshAccessToken(refreshToken: String): String {
        val authApiService = authApiServiceProvider.get()
        val response = authApiService.refreshToken(refreshToken)
        return response.accessToken
    }
}