package org.chosun.dodamduck.network.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.chosun.dodamduck.network.auth.AuthAuthenticator
import org.chosun.dodamduck.network.auth.AuthInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    @Named(NetworkType.DODAMDUCK)
    fun provideDodamDuckRetrofit(
        authAuthenticator: AuthAuthenticator,
        authInterceptor: AuthInterceptor
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("http://sy2978.dothome.co.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                provideOkHttpClient(
                    authAuthenticator,
                    httpLoggingInterceptor,
                    authInterceptor
                )
            )
            .build()

    @Singleton
    @Provides
    @Named(NetworkType.OPEN_API)
    fun provideOpenApiRetrofit(authAuthenticator: AuthAuthenticator): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://api.odcloud.kr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                provideOkHttpClient(
                    authAuthenticator,
                    httpLoggingInterceptor
                )
            )
            .build()
}


private fun provideOkHttpClient(
    authAuthenticator: AuthAuthenticator,
    vararg interceptor: Interceptor
): OkHttpClient =
    OkHttpClient.Builder().apply {
        interceptor.forEach { addInterceptor(it) }
        authenticator(authAuthenticator)
    }.build()

var httpLoggingInterceptor = HttpLoggingInterceptor { log ->
    Log.d("OkHttp", log)
}.apply {
    level = HttpLoggingInterceptor.Level.BASIC
}

object NetworkType {
    const val DODAMDUCK = "dodamDuck"
    const val OPEN_API = "openApi"
}