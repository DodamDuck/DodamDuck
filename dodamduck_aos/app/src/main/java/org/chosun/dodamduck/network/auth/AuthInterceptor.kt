package org.chosun.dodamduck.network.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
): Interceptor {

    companion object {
        private const val UNAUTHORIZED_ERROR = 401
        private const val AUTHORIZATION = "AUTHORIZATION"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String? = runBlocking { tokenManager.accessToken.first() }

        val request = chain.request().newBuilder().header(AUTHORIZATION, "Bearer $token").build()

        val response = chain.proceed(request)
        if(response.code == HTTP_OK) {
            val newAccessToken = response.header(AUTHORIZATION, null) ?: return response

            CoroutineScope(Dispatchers.IO).launch {
                val existedAccessToken = tokenManager.accessToken.first()
                if(existedAccessToken != newAccessToken) {
                    tokenManager.saveAccessToken(newAccessToken)
                }
            }
        }

        return response
    }
}