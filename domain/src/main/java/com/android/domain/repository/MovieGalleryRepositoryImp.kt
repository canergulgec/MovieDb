package com.android.domain.repository

import com.caner.common.Resource
import com.caner.common.ext.filterResponse
import com.android.domain.api.MovieImagesApi
import com.android.domain.api.MovieVideosApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieGalleryRepositoryImp @Inject constructor(
    private val imageApiService: MovieImagesApi,
    private val videoApiService: MovieVideosApi
) : MovieGalleryRepository {

    override fun getMovieGallery(movieId: Int?) = flow {
        emit(Resource.Loading)
        val imageData = imageApiService.getMovieImages(movieId)
        val videoData = videoApiService.getMovieVideos(movieId)

        emit(imageData.filterResponse())
        emit(videoData.filterResponse())
    }
}