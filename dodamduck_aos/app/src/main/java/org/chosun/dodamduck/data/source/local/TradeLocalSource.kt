package org.chosun.dodamduck.data.source.local

import org.chosun.dodamduck.database.SearchHistory
import org.chosun.dodamduck.database.SearchHistoryDao
import javax.inject.Inject

class TradeLocalSource @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
) {

    suspend fun getAllSearchHistory(): List<SearchHistory> = searchHistoryDao.getAllSearchHistory()

    suspend fun insertSearchQuery(searchHistory: SearchHistory) =
        searchHistoryDao.insertSearchQuery(searchHistory)


    suspend fun deleteAll() = searchHistoryDao.deleteAll()

    suspend fun deleteSearchQuery(query: String) = searchHistoryDao.deleteSearchQuery(query)

}
