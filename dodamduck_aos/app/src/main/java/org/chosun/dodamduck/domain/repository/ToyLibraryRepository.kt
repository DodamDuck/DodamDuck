package org.chosun.dodamduck.domain.repository

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.data.dto.library.ToyLibraryDto

interface ToyLibraryRepository {
    suspend fun fetchToyList(): Flow<ApiResult<List<ToyLibraryDto>>>
}