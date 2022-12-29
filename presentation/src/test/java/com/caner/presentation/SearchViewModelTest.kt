package com.caner.presentation

import app.cash.turbine.test
import com.caner.domain.model.Movie
import com.caner.domain.model.MovieModel
import com.caner.domain.usecase.SearchMovieUseCase
import com.caner.domain.utils.state.Resource
import com.caner.navigation.NavigationDispatcher
import com.caner.presentation.ui.search.viewmodel.SearchViewModel
import com.caner.presentation.utils.MainCoroutineScopeRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
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
    private val mockNavigationDispatcher = mockk<NavigationDispatcher>()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUpViewModel() {
        viewModel = SearchViewModel(mockUseCase, mockNavigationDispatcher)
    }

    @Test
    fun `Should return successful response when asked for search movie`() = runTest {
        // Given
        val id = 1
        val mockMovieItem = mockk<Movie> {
            every { movieId } returns id
        }
        val mockDetailModel = mockk<MovieModel> {
            every { movies } returns listOf(mockMovieItem)
        }
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(mockDetailModel))
            emit(Resource.Loading(false))
        }
        coEvery { mockUseCase.searchMovie(any()) } returns flow

        viewModel.uiState.test {
            // When
            viewModel.searchQuery.value = "Spider"

            // Then
            assertThat(awaitItem().isFetchingMovies).isFalse()
            assertThat(awaitItem().isFetchingMovies).isTrue()

            val item = awaitItem()
            assertThat(item.movieList).isNotEmpty()
            assertThat(item.movieList.first().movieId).isEqualTo(id)
            assertThat(awaitItem().isFetchingMovies).isFalse()

            ensureAllEventsConsumed()
        }
        // Verify
        coVerify(exactly = 1) { mockUseCase.searchMovie(any()) }
    }
}
