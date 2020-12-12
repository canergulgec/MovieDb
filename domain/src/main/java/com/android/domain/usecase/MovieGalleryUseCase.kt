package com.android.domain.usecase

import com.android.base.BaseUseCase
import com.android.domain.di.IoDispatcher
import com.android.domain.repository.MovieGalleryRepository
import com.caner.common.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieGalleryUseCase @Inject constructor(
    private val galleryRepository: MovieGalleryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<Any, Int>() {

    override fun buildRequest(params: Int?): Flow<Resource<Any>> {
        return galleryRepository.getMovieGallery(params)
            .flowOn(dispatcher)
    }
}