package org.chosun.dodamduck.data.source.local

import org.chosun.dodamduck.data.database.SearchHistory
import org.chosun.dodamduck.data.database.SearchHistoryDao
import javax.inject.Inject

class PostLocalSource @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
) {

    suspend fun getAllSearchHistory(): List<SearchHistory> {
        return searchHistoryDao.getAllSearchHistory()
    }

    suspend fun insertSearchQuery(searchHistory: SearchHistory) {
        searchHistoryDao.insertSearchQuery(searchHistory)
    }

    suspend fun deleteAll() {
        searchHistoryDao.deleteAll()
    }

}
