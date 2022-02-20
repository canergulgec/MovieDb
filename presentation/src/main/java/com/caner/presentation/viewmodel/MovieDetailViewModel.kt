package com.caner.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.core.network.Resource
import com.caner.core.network.UserMessage
import com.caner.domain.usecase.MovieDetailUseCase
import com.caner.presentation.viewmodel.state.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieDetailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    fun getMovieDetail(movieId: Int?) {
        viewModelScope.launch {
            useCase.execute(movieId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update { it.copy(movieDetailModel = resource.data) }
                    }
                    is Resource.Error -> {
                        _uiState.update { state ->
                            state.errorMessages.add(UserMessage(resource.error.message))
                            state.copy(errorMessages = state.errorMessages)
                        }
                    }
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isFetchingMovieDetail = resource.status) }
                    }
                }
            }
        }
    }

    fun userMessageShown() {
        _uiState.update { it.copy(errorMessages = mutableListOf()) }
    }
}
