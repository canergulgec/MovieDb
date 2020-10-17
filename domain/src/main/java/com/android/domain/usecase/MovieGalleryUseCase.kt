package com.android.domain.usecase

import com.android.base.Resource
import com.android.base.UseCase
import com.android.domain.di.IoDispatcher
import com.android.domain.repository.MovieGalleryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieGalleryUseCase @Inject constructor(
    private val galleryRepository: MovieGalleryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Any, Int>() {

    override suspend fun buildRequest(params: Int?): Flow<Resource<Any>> {
        return galleryRepository.getMovieGallery(params)
            .flowOn(dispatcher)
    }
}