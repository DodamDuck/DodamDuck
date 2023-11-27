package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.ToyInfo
import org.chosun.dodamduck.model.network.ToyLibraryApiService
import javax.inject.Inject

class ToyLibraryRepository @Inject constructor(
    private val service: ToyLibraryApiService?
) {
    suspend fun fetchToyList(): List<ToyInfo> {
        val list = service?.getToyList()?.data ?: listOf()
        return list.sortedBy { it.toyName }.distinctBy { it.toyName }.take(30)
    }
}