package org.chosun.dodamduck.domain.usecase.remote.toylibrary

import kotlinx.coroutines.flow.Flow
import org.chosun.dodamduck.domain.model.ApiResult
import org.chosun.dodamduck.domain.repository.ToyLibraryRepository
import org.chosun.dodamduck.data.dto.library.ToyLibraryDto
import org.chosun.dodamduck.domain.usecase.remote.base.BaseResultUseCase
import javax.inject.Inject

class GetToys @Inject constructor(
    private val toyLibraryRepo: ToyLibraryRepository
): BaseResultUseCase<Nothing?, List<ToyLibraryDto>>() {
    override suspend fun execute(params: Nothing?): Flow<ApiResult<List<ToyLibraryDto>>> {
        return toyLibraryRepo.fetchToyList()
    }
}