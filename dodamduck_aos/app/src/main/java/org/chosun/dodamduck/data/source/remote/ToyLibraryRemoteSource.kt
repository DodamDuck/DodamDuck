package org.chosun.dodamduck.data.source.remote

import org.chosun.dodamduck.data.dto.ToyInfo
import org.chosun.dodamduck.network.ToyLibraryApiService
import javax.inject.Inject

class ToyLibraryRemoteSource @Inject constructor(
    private val service: ToyLibraryApiService
) {
    suspend fun fetchToys(): List<ToyInfo> {
        val list = service.getToyList().data ?: listOf()
        return list.sortedBy { it.toyName }.distinctBy { it.toyName }.take(30)
    }
}