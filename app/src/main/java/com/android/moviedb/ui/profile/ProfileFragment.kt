package com.android.moviedb.ui.profile

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.moviedb.R
import com.android.base.BaseFragment
import com.android.base.ext.observeWith
import com.android.data.Constants
import com.android.data.utils.DataStoreUtils
import com.android.presentation.vm.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var storeUtils: DataStoreUtils

    override val layoutId = R.layout.fragment_profile

    private val viewModel: ProfileViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        initObservers()

        //viewModel.getNewToken()
        lifecycleScope.launch {
            storeUtils.getData(Constants.ACCESS_TOKEN_DATA_STORE).collect { sessionId ->
                if (sessionId == null) {
                    viewModel.getNewTokenWithDataStore()
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.newSessionLiveData.observeWith(viewLifecycleOwner) {
            lifecycleScope.launch {
                storeUtils.saveData(Constants.ACCESS_TOKEN_DATA_STORE, it)
            }
        }
    }
}