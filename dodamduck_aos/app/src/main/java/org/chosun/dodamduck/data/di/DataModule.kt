package org.chosun.dodamduck.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.domain.repository.ToyLibraryRepository
import org.chosun.dodamduck.data.repository.AuthRepositoryImpl
import org.chosun.dodamduck.data.repository.ToyLibraryRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindAuthRepository(
        authRepo: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    fun bindToyLibraryRepository(
        toyLibraryRepo: ToyLibraryRepositoryImpl
    ): ToyLibraryRepository
}