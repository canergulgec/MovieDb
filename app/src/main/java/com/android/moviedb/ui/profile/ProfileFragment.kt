package com.android.moviedb.ui.profile

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.android.moviedb.R
import com.android.base.BaseFragment
import com.android.base.ext.observeWith
import com.android.presentation.vm.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_profile

    private val viewModel: ProfileViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        initObservers()

        viewModel.createNewSession()
    }

    private fun initObservers() {
        viewModel.newSessionLiveData.observeWith(viewLifecycleOwner) {
            val sessionId = it.sessionId
        }
    }
}