package org.chosun.dodamduck.data.source.remote

import org.chosun.dodamduck.data.dto.library.ToyLibraryDto
import org.chosun.dodamduck.network.service.ToyLibraryApiService
import javax.inject.Inject

class ToyLibraryRemoteSource @Inject constructor(
    private val service: ToyLibraryApiService
) {
    suspend fun fetchToys(): List<ToyLibraryDto> {
        val list = service.getToyList().data ?: listOf()
        return list.sortedBy { it.toyName }.distinctBy { it.toyName }.take(30)
    }
}