package org.chosun.dodamduck.model.repository

import org.chosun.dodamduck.model.dto.ToyInfo
import org.chosun.dodamduck.model.network.ToyLibraryApiService
import javax.inject.Inject

class ToyLibraryRepository @Inject constructor(
    private val service: ToyLibraryApiService?
) {
    suspend fun fetchToyList(): List<ToyInfo> {
        return service?.getToyList()?.data ?: listOf()
    }
}