package org.chosun.dodamduck.model.network

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named(NetworkType.DODAMDUCK)
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("http://sy2978.dothome.co.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                provideOkHttpClient(
                    httpLoggingInterceptor
                )
            )
            .build()
}

private fun provideOkHttpClient(vararg interceptor: Interceptor): OkHttpClient =
    OkHttpClient.Builder().run {
        interceptor.forEach { addInterceptor(it) }
        build()
    }

var httpLoggingInterceptor = HttpLoggingInterceptor {log ->
    Log.d("OkHttp", log)
}.apply {
    level = HttpLoggingInterceptor.Level.BODY
}
object NetworkType {
    const val DODAMDUCK = "dodamDuck"
}