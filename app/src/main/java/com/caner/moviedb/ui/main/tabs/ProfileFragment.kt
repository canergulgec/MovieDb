package com.caner.moviedb.ui.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.caner.presentation.viewmodel.ProfileViewModel
import com.caner.data.local.PrefKeys
import com.caner.core.network.Resource
import com.caner.data.local.PrefStore
import com.caner.core.base.BaseFragment
import com.caner.core.extension.toast
import com.caner.moviedb.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
            val sessionId = prefStore.getData(PrefKeys.ACCESS_TOKEN_DATA_STORE)
            if (sessionId == null) {
                viewModel.getNewToken()
            }
        }
    }

    private fun initObservers() {
        viewModel.newSessionLiveData.observe(viewLifecycleOwner) { resource ->
            viewLifecycleOwner.lifecycleScope.launch {
                when (resource) {
                    is Resource.Loading -> showLoading(resource.status)
                    is Resource.Success -> prefStore.saveData(
                            PrefKeys.ACCESS_TOKEN_DATA_STORE,
                            resource.data.requestToken
                    )
                    is Resource.Error -> toast(resource.error.message)
                    else -> Timber.v("Initial state")
                }
            }
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate
}
