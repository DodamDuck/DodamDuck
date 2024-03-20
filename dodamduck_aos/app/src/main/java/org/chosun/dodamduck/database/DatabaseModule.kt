package org.chosun.dodamduck.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DodamDuckDatabase =
        Room.databaseBuilder(
            appContext,
            DodamDuckDatabase::class.java,
            "search_history_database"
        ).build()

    @Provides
    fun provideSearchHistoryDao(database: DodamDuckDatabase): SearchHistoryDao {
        return database.searchHistoryDao()
    }
}