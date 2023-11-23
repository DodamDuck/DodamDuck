package org.chosun.dodamduck.model.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.chosun.dodamduck.model.network.AuthApiService
import org.chosun.dodamduck.model.network.ChatApiService
import org.chosun.dodamduck.model.network.RetrofitClient
import org.chosun.dodamduck.model.network.ToyLibraryApiService
import org.chosun.dodamduck.model.network.TradeApiService
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val OPEN_API_BASE_URL = "https://api.odcloud.kr/api/"
    private const val DODAM_DUCK_API_BASE_URL = "http://sy2978.dothome.co.kr/"

    @Provides
    @Named("OpenApiRetrofit")
    fun provideOpenApiRetrofit(): Retrofit {
        return RetrofitClient.getRetrofit(OPEN_API_BASE_URL)
    }

    @Provides
    @Named("DodamDuckRetrofit")
    fun provideDodamDuckRetrofit(): Retrofit {
        return RetrofitClient.getRetrofit(DODAM_DUCK_API_BASE_URL)
    }

    @Provides
    fun provideToyLibraryApiService(@Named("OpenApiRetrofit") retrofit: Retrofit): ToyLibraryApiService {
        return retrofit.create(ToyLibraryApiService::class.java)
    }

    @Provides
    fun provideTradeApiService(@Named("DodamDuckRetrofit") retrofit: Retrofit): TradeApiService {
        return retrofit.create(TradeApiService::class.java)
    }

    @Provides
    fun provideChatApiService(@Named("DodamDuckRetrofit") retrofit: Retrofit): ChatApiService {
        return retrofit.create(ChatApiService::class.java)
    }

    @Provides
    fun provideAuthApiService(@Named("DodamDuckRetrofit") retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }
}