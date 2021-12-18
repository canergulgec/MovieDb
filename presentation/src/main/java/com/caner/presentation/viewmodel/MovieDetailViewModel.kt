package com.caner.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.domain.usecase.MovieDetailUseCase
import com.caner.data.viewstate.Resource
import com.caner.data.viewstate.UserMessage
import com.caner.presentation.state.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
        private val movieDetailUseCase: MovieDetailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    fun getMovieDetail(movieId: Int?) {
        viewModelScope.launch {
            movieDetailUseCase.execute(movieId)
                    .collect { resource ->
                        when (resource) {
                            is Resource.Success -> {
                                _uiState.update { state ->
                                    state.copy(movieDetailModel = resource.data,
                                            genres = resource.data.genres)
                                }
                            }
                            is Resource.Loading -> {
                                _uiState.update { state ->
                                    state.copy(isFetchingMovieDetail = resource.status)
                                }
                            }
                            is Resource.Error -> {
                                _uiState.update { state ->
                                    val messages = state.userMessages + UserMessage(
                                            id = UUID.randomUUID().mostSignificantBits,
                                            message = resource.apiError.message
                                    )
                                    state.copy(userMessages = messages)
                                }
                            }
                            else -> Timber.e("")
                        }
                    }
        }
    }

    fun userMessageShown(messageId: Long) {
        _uiState.update { currentUiState ->
            val messages = currentUiState.userMessages.filterNot { it.id == messageId }
            currentUiState.copy(userMessages = messages)
        }
    }
}
