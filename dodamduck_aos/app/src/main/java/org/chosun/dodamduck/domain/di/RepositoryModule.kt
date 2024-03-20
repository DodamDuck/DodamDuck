package org.chosun.dodamduck.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import org.chosun.dodamduck.data.dto.post.PostDto
import org.chosun.dodamduck.data.dto.trade.Trade
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
        postRepo: PostRepositoryImpl<PostDto>
    ): PostRepository<PostDto>

    @Binds
    @ViewModelScoped
    fun bindTradeRepository(
        tradeRepo: TradeRepositoryImpl<Trade>
    ): TradeRepository<Trade>

    @Binds
    @ViewModelScoped
    fun bindBasePostRepository(
        postRepo: PostRepositoryImpl<PostDto>
    ): BasePostRepository<PostDto>

    @Binds
    @ViewModelScoped
    fun bindBaseTradeRepository(
        tradeRepo: TradeRepositoryImpl<Trade>
    ): BasePostRepository<Trade>
}