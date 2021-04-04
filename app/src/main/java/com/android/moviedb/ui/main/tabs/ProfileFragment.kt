package com.android.moviedb.ui.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.base.BaseFragment
import com.android.moviedb.databinding.FragmentProfileBinding
import com.android.presentation.vm.ProfileViewModel
import com.caner.common.PrefKeys
import com.caner.common.Resource
import com.caner.common.ext.observeWith
import com.caner.common.ext.toast
import com.caner.common.utils.PrefStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    @Inject
    lateinit var prefStore: PrefStore

    private val viewModel: ProfileViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        initObservers()

        lifecycleScope.launch {
            prefStore.getData(PrefKeys.ACCESS_TOKEN_DATA_STORE).collect { sessionId ->
                if (sessionId == null) {
                    viewModel.getNewTokenWithDataStore()
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.newSessionLiveData.observeWith(viewLifecycleOwner) { resource ->
            lifecycleScope.launch {
                when (resource) {
                    is Resource.Loading -> showLoading(resource.status)
                    is Resource.Success -> prefStore.saveData(PrefKeys.ACCESS_TOKEN_DATA_STORE, resource.data.requestToken)
                    is Resource.Error -> toast(resource.apiError.message)
                    is Resource.Empty -> Timber.v("Initial Empty state")
                }
            }
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate
}
