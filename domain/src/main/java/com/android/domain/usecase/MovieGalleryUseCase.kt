package com.android.domain.usecase

import com.android.base.UseCase
import com.android.domain.repository.MovieImagesRepository
import com.android.domain.repository.MovieVideosRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieGalleryUseCase @Inject constructor(
    private val imagesRepository: MovieImagesRepository,
    private val videosRepository: MovieVideosRepository
) :
    UseCase<ArrayList<Any>, Int>() {

    override fun buildUseCaseObservable(params: Int?): Single<ArrayList<Any>> {
        val movieGalleryList = arrayListOf<Any>()

        return imagesRepository.getMovieImages(params).flatMap {
            movieGalleryList.addAll(it.backdrops)
            videosRepository.getMovieVideos(params).flatMap { videoResponse ->
                movieGalleryList.addAll(videoResponse.results)
                Single.just(movieGalleryList)
            }
        }
    }
}