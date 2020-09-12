package com.android.domain.repository

import com.android.base.Resource
import com.android.data.model.remote.MovieImagesResponse
import kotlinx.coroutines.flow.Flow

interface MovieGalleryRepository {
    fun getMovieGallery(movieId: Int?): Flow<Resource<Any>>
}