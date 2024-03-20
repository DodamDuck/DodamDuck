package org.chosun.dodamduck.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.domain.repository.ToyLibraryRepository
import org.chosun.dodamduck.data.repository.AuthRepositoryImpl
import org.chosun.dodamduck.data.repository.ChatRepositoryImpl
import org.chosun.dodamduck.data.repository.PostRepositoryImpl
import org.chosun.dodamduck.data.repository.ToyLibraryRepositoryImpl
import org.chosun.dodamduck.data.repository.TradeRepositoryImpl
import org.chosun.dodamduck.domain.repository.BasePostRepository
import org.chosun.dodamduck.domain.repository.ChatRepository
import org.chosun.dodamduck.domain.repository.PostRepository
import org.chosun.dodamduck.domain.repository.TradeRepository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    @ViewModelScoped
    fun bindAuthRepository(
        authRepo: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @ViewModelScoped
    fun bindToyLibraryRepository(
        toyLibraryRepo: ToyLibraryRepositoryImpl
    ): ToyLibraryRepository

    @Binds
    @ViewModelScoped
    fun bindChatRepository(
        chatRepo: ChatRepositoryImpl
    ): ChatRepository

    @Binds
    @ViewModelScoped
    fun bindPostRepository(
        postRepo: PostRepositoryImpl<PostDTO>
    ): PostRepository<PostDTO>

    @Binds
    @ViewModelScoped
    fun bindTradeRepository(
        tradeRepo: TradeRepositoryImpl<Trade>
    ): TradeRepository<Trade>

    @Binds
    @ViewModelScoped
    fun bindBasePostRepository(
        postRepo: PostRepositoryImpl<PostDTO>
    ): BasePostRepository<PostDTO>

    @Binds
    @ViewModelScoped
    fun bindBaseTradeRepository(
        tradeRepo: TradeRepositoryImpl<Trade>
    ): BasePostRepository<Trade>
}