package com.android.presentation.vm

import androidx.lifecycle.ViewModel
import com.android.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchMovieUseCase
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    val searchFlow = searchQuery
        .debounce(400)
        .distinctUntilChanged()
        .filter { query ->
            return@filter query.length > 2
        }
        .flatMapLatest {
            searchUseCase.execute(it)
        }
}