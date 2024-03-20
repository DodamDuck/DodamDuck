package org.chosun.dodamduck.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.chosun.dodamduck.network.AuthApiService
import org.chosun.dodamduck.network.ChatApiService
import org.chosun.dodamduck.network.NetworkType
import org.chosun.dodamduck.network.PostApiService
import org.chosun.dodamduck.network.ToyLibraryApiService
import org.chosun.dodamduck.network.TradeApiService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthApiService(
        @Named(NetworkType.DODAMDUCK) retrofit: Retrofit
    ) = retrofit.create<AuthApiService>()

    @Provides
    @Singleton
    fun provideToyLibraryApiService(
        @Named(NetworkType.OPEN_API) retrofit: Retrofit
    ) = retrofit.create<ToyLibraryApiService>()

    @Provides
    @Singleton
    fun provideTradeApiService(
        @Named(NetworkType.DODAMDUCK) retrofit: Retrofit
    ) = retrofit.create<TradeApiService>()

    @Provides
    @Singleton
    fun provideChatApiService(
        @Named(NetworkType.DODAMDUCK) retrofit: Retrofit
    ) = retrofit.create<ChatApiService>()

    @Provides
    @Singleton
    fun providePostApiService(
        @Named(NetworkType.DODAMDUCK) retrofit: Retrofit
    ) = retrofit.create<PostApiService>()
}