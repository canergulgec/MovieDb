package com.android.test

import com.android.data.model.MovieDetailModel
import com.android.domain.repository.MovieDetailRepository
import com.android.domain.repository.MovieGalleryRepository
import com.android.domain.usecase.MovieDetailUseCase
import com.android.domain.usecase.MovieGalleryUseCase
import com.android.presentation.vm.MovieDetailViewModel
import com.android.test.util.MainCoroutineScopeRule
import com.caner.common.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val detailRepository: MovieDetailRepository = mock()
    private val galleryRepository: MovieGalleryRepository = mock()

    private val detailUseCase by lazy {
        MovieDetailUseCase(detailRepository, coroutineScope.dispatcher)
    }

    private val galleryUseCase by lazy {
        MovieGalleryUseCase(galleryRepository, coroutineScope.dispatcher)
    }

    private val viewModel by lazy { MovieDetailViewModel(detailUseCase, galleryUseCase) }

    @Test
    fun movieDetailStateFlowMustReturnSuccess() = runBlocking {
        //Given
        val detailModel: MovieDetailModel = mock()
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(detailModel))
            emit(Resource.Loading(false))
        }

        //When
        whenever(detailRepository.getMovieDetail(3)).thenReturn(flow)

        //Then
        viewModel.getMovieDetail(3)

        Assert.assertEquals(viewModel.movieDetailState.value is Resource.Success, true)
    }
}