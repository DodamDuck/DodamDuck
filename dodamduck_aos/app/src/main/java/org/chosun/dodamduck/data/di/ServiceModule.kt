package org.chosun.dodamduck.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.data.repository.PostRepositoryImpl
import org.chosun.dodamduck.data.repository.TradeRepositoryImpl
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.repository.PostRepository
import org.chosun.dodamduck.domain.repository.TradeRepository
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

    @Provides
    fun providePostRepository(impl: PostRepositoryImpl<PostDTO>): PostRepository<PostDTO> = impl

    @Provides
    fun provideTradeRepository(impl: TradeRepositoryImpl<Trade>): TradeRepository<Trade> = impl

    @Provides
    fun provideBasePostRepository(impl: PostRepositoryImpl<PostDTO>): BasePostRepository<PostDTO> = impl

    @Provides
    fun provideBaseTradeRepository(impl: TradeRepositoryImpl<Trade>): BasePostRepository<Trade> = impl
}