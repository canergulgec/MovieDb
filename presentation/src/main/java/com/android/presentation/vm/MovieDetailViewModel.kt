package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.base.ApiError
import com.android.base.BaseViewModel
import com.android.base.NetworkState
import com.android.data.model.MovieDetailModel
import com.android.data.model.remote.BackdropItem
import com.android.data.model.remote.VideoItem
import com.android.domain.usecase.MovieDetailUseCase
import com.android.domain.usecase.MovieGalleryUseCase
import io.reactivex.observers.DisposableSingleObserver

class MovieDetailViewModel @ViewModelInject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val movieGalleryUseCase: MovieGalleryUseCase
) : BaseViewModel() {

    val movieDetailLiveData: MutableLiveData<MovieDetailModel> = MutableLiveData()
    val movieGalleryLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val movieImageList = arrayListOf<BackdropItem>()
    val movieVideoList = arrayListOf<VideoItem>()

    fun getMovieDetail(movieId: Int?) {
        setNetworkStatus(NetworkState.Loading)

        add(
            movieDetailUseCase.execute(movieId)
                .subscribeWith(object : DisposableSingleObserver<MovieDetailModel>() {
                    override fun onSuccess(t: MovieDetailModel) {
                        setNetworkStatus(NetworkState.Success)
                        movieDetailLiveData.value = t
                    }

                    override fun onError(e: Throwable) = setError(e as ApiError)
                })
        )
    }

    fun getMovieGallery(movieId: Int?) {
        setNetworkStatus(NetworkState.Loading)

        add(
            movieGalleryUseCase.execute(movieId)
                .subscribeWith(object : DisposableSingleObserver<ArrayList<Any>>() {
                    override fun onSuccess(t: ArrayList<Any>) {
                        setNetworkStatus(NetworkState.Success)
                        apartMovieGalleryList(t)
                        movieGalleryLiveData.value = true
                    }

                    override fun onError(e: Throwable) = setError(e as ApiError)
                })
        )
    }

    private fun apartMovieGalleryList(movieGalleryList: ArrayList<Any>) {
        movieGalleryList.forEachIndexed { index, item ->
            if (item is BackdropItem) movieImageList.add(item)
            else if (item is VideoItem) movieVideoList.add(item)
        }
    }
}