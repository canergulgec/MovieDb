package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.android.base.BaseViewModel
import com.caner.common.Constants
import com.caner.common.Resource
import com.android.data.model.MovieDetailModel
import com.android.data.model.remote.BackdropItem
import com.android.data.model.remote.MovieImagesResponse
import com.android.data.model.remote.MovieVideosResponse
import com.android.data.model.remote.VideoItem
import com.android.domain.usecase.MovieDetailUseCase
import com.android.domain.usecase.MovieGalleryUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val movieGalleryUseCase: MovieGalleryUseCase
) : BaseViewModel() {

    private val _movieDetailState = MutableStateFlow<Resource<MovieDetailModel>>(Resource.Empty)
    val movieDetailState: StateFlow<Resource<MovieDetailModel>> get() = _movieDetailState

    private val _movieBackdropState = MutableStateFlow<Resource<List<BackdropItem>>>(Resource.Empty)
    val movieBackdropState: StateFlow<Resource<List<BackdropItem>>> get() = _movieBackdropState

    private val _movieVideoState = MutableStateFlow<Resource<List<VideoItem>>>(Resource.Empty)
    val movieVideoState: StateFlow<Resource<List<VideoItem>>> get() = _movieVideoState

    fun getMovieDetail(movieId: Int?) {
        viewModelScope.launch {
            movieDetailUseCase.execute(movieId).collect {
                when (it) {
                    is Resource.Loading -> setLoadingStatus(it.status)
                    is Resource.Success -> _movieDetailState.value = it
                    is Resource.Error -> setError(it.apiError)
                    is Resource.Empty -> Constants.pass
                }
            }
        }
    }

    fun getMovieGallery(movieId: Int?) {
        viewModelScope.launch {
            movieGalleryUseCase.execute(movieId).collect {
                when (it) {
                    is Resource.Loading -> setLoadingStatus(it.status)
                    is Resource.Success -> apartMovieGalleryList(it.data)
                    is Resource.Error -> setError(it.apiError)
                    is Resource.Empty -> Constants.pass
                }
            }
        }
    }

    private fun apartMovieGalleryList(movieGalleryItem: Any) {
        when (movieGalleryItem) {
            is MovieImagesResponse -> _movieBackdropState.value =
                Resource.Success(movieGalleryItem.backdrops)
            is MovieVideosResponse -> _movieVideoState.value =
                Resource.Success(movieGalleryItem.results)
        }
    }
}