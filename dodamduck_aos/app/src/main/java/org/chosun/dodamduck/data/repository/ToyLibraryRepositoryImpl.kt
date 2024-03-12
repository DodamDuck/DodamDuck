package org.chosun.dodamduck.data.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ToyLibraryRepository
import org.chosun.dodamduck.data.dto.ToyInfo
import org.chosun.dodamduck.data.safeFlow
import org.chosun.dodamduck.data.source.remote.ToyLibraryRemoteSource
import javax.inject.Inject

class ToyLibraryRepositoryImpl @Inject constructor(
    private val toyLibraryRemoteSource: ToyLibraryRemoteSource
): ToyLibraryRepository {
    override suspend fun fetchToyList(): Flow<ApiResult<List<ToyInfo>>> = safeFlow {
        toyLibraryRemoteSource.fetchToys()
    }
}