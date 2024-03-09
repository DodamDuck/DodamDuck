package org.chosun.dodamduck.model.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.chosun.dodamduck.domain.repository.AuthRepository
import org.chosun.dodamduck.model.repository.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindAuthRepository(
        authRepo: AuthRepositoryImpl
    ): AuthRepository
}