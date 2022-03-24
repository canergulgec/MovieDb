package com.caner.moviedb.ui.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.caner.presentation.adapter.recyclerview.MovieSearchAdapter
import com.caner.presentation.viewmodel.SearchViewModel
import com.caner.core.base.BaseFragment
import com.caner.core.extension.*
import com.caner.presentation.adapter.decoration.VerticalSpaceItemDecoration
import com.caner.moviedb.databinding.FragmentSearchBinding
import com.caner.presentation.viewmodel.TextEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter by lazy {
        MovieSearchAdapter(onClick = {
            movieClicked(it?.movieId)
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    showLoading(state.isFetchingMovies)
                    state.movieList.takeIf { !it.isNullOrEmpty() }.run {
                        searchAdapter.submitList(this)
                    }
                    binding.emptyViewTv.visible(state.movieList.isNullOrEmpty())
                }
            }
        }
    }

    private fun movieClicked(movieId: Int?) {
        val detailAction = SearchFragmentDirections.movieDetailAction(movieId ?: 0)
        findNavController().navigate(detailAction)
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
}
