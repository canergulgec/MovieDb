package com.caner.presentation

import app.cash.turbine.test
import com.caner.domain.model.Movie
import com.caner.domain.model.MovieImage
import com.caner.domain.model.MovieModel
import com.caner.domain.usecase.SearchMovieUseCase
import com.caner.domain.utils.state.Resource
import com.caner.navigation.NavigationDispatcher
import com.caner.presentation.ui.search.viewmodel.SearchViewModel
import com.caner.presentation.utils.MainCoroutineScopeRule
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val mockUseCase = mockk<SearchMovieUseCase>()
    private val navigationDispatcher = mockk<NavigationDispatcher>()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUpViewModel() {
        viewModel = SearchViewModel(mockUseCase, navigationDispatcher)
    }

    @Test
    fun `Get movie search from viewModel should return success case`() = runTest {
        // Given
        val movie = Movie(
            movieId = 1,
            popularity = 2.0,
            video = false,
            adult = false,
            poster = MovieImage(""),
            backdrop = MovieImage(""),
            originalLanguage = "en",
            originalTitle = "Spider-Man",
            title = "Spider-Man",
            voteAverage = 4.0,
            overview = "",
            releaseDate = null
        )

        val detailModel = MovieModel(movies = listOf(movie))
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(detailModel))
            emit(Resource.Loading(false))
        }
        coEvery { mockUseCase.searchMovie(any()) } returns flow

        viewModel.uiState.test {
            // When
            viewModel.searchQuery.value = "Spider"

            // Then
            Truth.assertThat(awaitItem().isFetchingMovies).isFalse()
            Truth.assertThat(awaitItem().isFetchingMovies).isTrue()

            val item = awaitItem()
            Truth.assertThat(item.movieList).isNotEmpty()
            Truth.assertThat(item.movieList.first()).isEqualTo(movie)

            Truth.assertThat(awaitItem().isFetchingMovies).isFalse()

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
    }
}
