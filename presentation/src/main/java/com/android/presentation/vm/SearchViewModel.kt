package com.android.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.SharingStarted
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
        .filter { query ->
            return@filter query.length > 2
        }
        .flatMapLatest {
            searchUseCase.execute(it)
        }.shareIn(viewModelScope, SharingStarted.Lazily, 2)
}
