package com.caner.presentation.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.caner.presentation.utils.widget.FullScreenProgressDialog
import com.caner.presentation.R
import com.caner.presentation.utils.decoration.VerticalSpaceItemDecoration
import com.caner.presentation.databinding.FragmentSearchBinding
import com.caner.presentation.ui.search.adapter.SearchAdapter
import com.caner.presentation.ui.search.viewmodel.SearchViewModel
import com.caner.presentation.ui.search.viewmodel.TextEvent
import com.caner.presentation.utils.delegate.viewBinding
import com.caner.presentation.utils.extension.afterTextChanged
import com.caner.presentation.utils.extension.init
import com.caner.presentation.utils.extension.px
import com.caner.presentation.utils.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()

    private val progressDialog by lazy { FullScreenProgressDialog(requireContext()) }

    private val searchAdapter by lazy {
        SearchAdapter(onClick = { movieID ->
            viewModel.navigateToMovieDetail(SearchFragmentDirections.movieDetailAction(movieID))
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            showLoadingDialog(state.isFetchingMovies)
            state.movieList.takeIf { it.isNotEmpty() }.run {
                searchAdapter.submitList(this)
            }
            binding.emptyViewTv.visible(state.movieList.isEmpty())
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showLoadingDialog(isFetching: Boolean) {
        if (isFetching && !progressDialog.isShowing) {
            progressDialog.show()
        } else {
            progressDialog.hide()
        }
    }
}
