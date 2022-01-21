package com.caner.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.core.network.Resource
import com.caner.core.network.UserMessage
import com.caner.domain.usecase.MovieDetailUseCase
import com.caner.presentation.state.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieDetailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailUiState(isFetchingMovieDetail = true))
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    fun getMovieDetail(movieId: Int?) {
        viewModelScope.launch {
            useCase.execute(movieId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                movieDetailModel = resource.data,
                                isFetchingMovieDetail = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { state ->
                            val messages = state.userMessages + UserMessage(
                                id = UUID.randomUUID().mostSignificantBits,
                                message = resource.error.message
                            )
                            state.copy(userMessages = messages, isFetchingMovieDetail = false)
                        }
                    }
                    is Resource.Loading -> {
                        // No need to implement
                    }
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
