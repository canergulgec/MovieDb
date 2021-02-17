package com.android.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.data.model.MovieDetailModel
import com.android.domain.repository.MovieDetailRepository
import com.android.domain.repository.MovieGalleryRepository
import com.android.domain.usecase.MovieDetailUseCase
import com.android.domain.usecase.MovieGalleryUseCase
import com.android.presentation.vm.MovieDetailViewModel
import com.android.test.utils.MainCoroutineScopeRule
import com.caner.common.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

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
    fun movieDetailFlowMustReturnSuccess() = runBlocking {
        //Given
        val detailModel: MovieDetailModel = mock()
        val flow = flow {
            //  emit(Resource.Loading(true))
            emit(Resource.Success(detailModel))
            //  emit(Resource.Loading(false))
        }

        //When
        whenever(detailUseCase.execute(13)).thenReturn(flow)

        //Then
        viewModel.getMovieDetail(13)

        viewModel.movieDetailState.collectIndexed { index, value ->
            if (index == 0) assert(value is Resource.Success)
        }

    }
}