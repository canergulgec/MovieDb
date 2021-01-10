package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import com.android.base.BaseViewModel
import com.android.domain.usecase.SearchMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
class SearchViewModel @ViewModelInject constructor(
    private val searchUseCase: SearchMovieUseCase
) : BaseViewModel() {

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