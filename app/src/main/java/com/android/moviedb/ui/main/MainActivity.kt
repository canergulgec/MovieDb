package com.android.moviedb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.base.BaseActivity
import com.android.moviedb.R
import com.android.moviedb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController by lazy { findNavController(R.id.mainNavHostFragment) }

    override fun initView(savedInstanceState: Bundle?) {
        setupViews()
    }

    private fun setupViews() {
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
}