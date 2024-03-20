package org.chosun.dodamduck.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SearchHistory:: class], version = 1, exportSchema = false)
abstract class DodamDuckDatabase: RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}
