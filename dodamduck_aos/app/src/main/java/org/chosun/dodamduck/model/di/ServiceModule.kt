package org.chosun.dodamduck.model.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.chosun.dodamduck.model.network.AuthApiService
import org.chosun.dodamduck.model.network.ChatApiService
import org.chosun.dodamduck.model.network.NetworkType
import org.chosun.dodamduck.model.network.PostApiService
import org.chosun.dodamduck.model.network.ToyLibraryApiService
import org.chosun.dodamduck.model.network.TradeApiService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideAuthApiService(
        @Named(NetworkType.DODAMDUCK) retrofit: Retrofit
    ) = retrofit.create<AuthApiService>()

    @Provides
    @Singleton
    fun provideToyLibraryApiService(
        @Named(NetworkType.OPEN_API) retrofit: Retrofit)
     = retrofit.create<ToyLibraryApiService>()

    @Provides
    @Singleton
    fun provideTradeApiService(
        @Named(NetworkType.DODAMDUCK) retrofit: Retrofit)
            = retrofit.create<TradeApiService>()

    @Provides
    @Singleton
    fun provideChatApiService(
        @Named(NetworkType.DODAMDUCK) retrofit: Retrofit)
            = retrofit.create<ChatApiService>()

    @Provides
    @Singleton
    fun providePostApiService(
        @Named(NetworkType.DODAMDUCK) retrofit: Retrofit)
            = retrofit.create<PostApiService>()
}