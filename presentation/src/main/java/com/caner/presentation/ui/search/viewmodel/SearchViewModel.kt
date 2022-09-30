package com.caner.presentation.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.caner.domain.utils.state.Resource
import com.caner.domain.utils.state.UserMessage
import com.caner.domain.usecase.SearchMovieUseCase
import com.caner.navigation.NavigationDispatcher
import com.caner.presentation.ui.search.viewmodel.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchMovieUseCase,
    private val navigationDispatcher: NavigationDispatcher
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        initSearch()
    }

    fun onEvent(event: TextEvent) {
        when (event) {
            is TextEvent.OnValueChange -> {
                searchQuery.value = event.text
            }
        }
    }

    private fun initSearch() {
        viewModelScope.launch {
            searchQuery.debounce(400)
                .filter { query ->
                    return@filter query.length > 2
                }
                .flatMapLatest { query ->
                    useCase.searchMovie(query)
                }
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _uiState.update { it.copy(movieList = resource.data.movies) }
                        }
                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(errorMessage = listOf(UserMessage(resource.error.message)))
                            }
                        }
                        is Resource.Loading -> {
                            _uiState.update { it.copy(isFetchingMovies = resource.status) }
                        }
                    }
                }
        }
    }

    fun navigateToMovieDetail(direction: NavDirections) {
        viewModelScope.launch {
            navigationDispatcher.navigateTo(direction)
        }
    }
}

sealed class TextEvent {
    data class OnValueChange(val text: String) : TextEvent()
}
