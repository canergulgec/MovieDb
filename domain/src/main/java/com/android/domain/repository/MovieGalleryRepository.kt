package com.android.domain.repository

import com.caner.common.Resource
import kotlinx.coroutines.flow.Flow

interface MovieGalleryRepository {
    fun getMovieGallery(movieId: Int?): Flow<Resource<Any>>
}