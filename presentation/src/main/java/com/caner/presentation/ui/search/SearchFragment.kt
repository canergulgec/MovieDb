package com.caner.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.caner.core.base.BaseFragment
import com.caner.core.extension.*
import com.caner.presentation.utils.decoration.VerticalSpaceItemDecoration
import com.caner.presentation.databinding.FragmentSearchBinding
import com.caner.presentation.ui.search.adapter.SearchAdapter
import com.caner.presentation.ui.search.viewmodel.SearchViewModel
import com.caner.presentation.ui.search.viewmodel.TextEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModels()

    private val searchAdapter by lazy {
        SearchAdapter(onClick = { movieID ->
            viewModel.navigateToMovieDetail(SearchFragmentDirections.movieDetailAction(movieID))
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.movieSearchRv.init(
            adapter = searchAdapter,
            itemDecoration = listOf(
                VerticalSpaceItemDecoration(16.px),
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            ),
            hasFixedSize = true
        )
        binding.searchEt.afterTextChanged {
            viewModel.onEvent(TextEvent.OnValueChange(it))
        }
        initObservers()
    }

    private fun initObservers() {
        viewModel.uiState.flowWithLifecycle(lifecycle = viewLifecycleOwner.lifecycle).onEach { state ->
            showLoading(state.isFetchingMovies)
            state.movieList.takeIf { it.isNotEmpty() }.run {
                searchAdapter.submitList(this)
            }
            binding.emptyViewTv.visible(state.movieList.isEmpty())
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
}
