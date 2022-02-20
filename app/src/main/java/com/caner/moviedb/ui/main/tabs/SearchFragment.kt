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
import com.caner.data.model.Movie
import com.caner.presentation.adapter.recyclerview.MovieSearchAdapter
import com.caner.presentation.viewmodel.SearchViewModel
import com.caner.core.network.Resource
import com.caner.core.base.BaseFragment
import com.caner.presentation.adapter.decoration.VerticalSpaceItemDecoration
import com.caner.core.extension.init
import com.caner.core.extension.dp2px
import com.caner.core.extension.afterTextChanged
import com.caner.core.extension.toast
import com.caner.core.extension.visible
import com.caner.moviedb.databinding.FragmentSearchBinding
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
        MovieSearchAdapter {
            movieClicked(it?.movieId)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.movieSearchRv.init(
            adapter = searchAdapter,
            itemDecoration = listOf(
                VerticalSpaceItemDecoration(16.dp2px()),
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            ),
            hasFixedSize = true
        )
        binding.searchEt.afterTextChanged {
            viewModel.searchQuery.value = it
        }
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchFlow.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> showLoading(resource.status)
                        is Resource.Success -> setList(resource.data.movies)
                        is Resource.Error -> toast(resource.error.message)
                    }
                }
            }
        }
    }

    private fun movieClicked(movieId: Int?) {
        val detailAction = SearchFragmentDirections.movieDetailAction(movieId ?: 0)
        findNavController().navigate(detailAction)
    }

    private fun setList(movies: List<Movie>) {
        binding.emptyViewTv.visible(movies.isNullOrEmpty())
        searchAdapter.submitList(movies)
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
}
