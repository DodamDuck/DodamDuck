package org.chosun.dodamduck.model.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.chosun.dodamduck.model.network.RetrofitClient
import org.chosun.dodamduck.model.network.ToyLibraryApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOpenApiRetrofit(): Retrofit {
        return RetrofitClient.getOpenApiRetrofit()
    }

    @Provides
    fun provideToyLibraryApiService(retrofit: Retrofit): ToyLibraryApiService {
        return retrofit.create(ToyLibraryApiService::class.java)
    }
}