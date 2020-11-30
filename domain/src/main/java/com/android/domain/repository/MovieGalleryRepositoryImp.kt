package com.android.domain.repository

import com.android.base.Resource
import com.android.base.ext.filterResponse
import com.android.domain.api.MovieImagesApi
import com.android.domain.api.MovieVideosApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MovieGalleryRepositoryImp @Inject constructor(
    private val imageApiService: MovieImagesApi,
    private val videoApiService: MovieVideosApi
) : MovieGalleryRepository {

    override fun getMovieGallery(movieId: Int?): Flow<Resource<Any>> {
        val images = flow {
            val data = imageApiService.getMovieImages(movieId)
            emit(Resource.Loading)
            emit(data.filterResponse())
        }

        val videos = flow {
            val data = videoApiService.getMovieVideos(movieId)
            emit(Resource.Loading)
            emit(data.filterResponse())
        }

        return merge(images, videos)
    }
}