package com.android.moviedb.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.moviedb.R
import com.android.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val navController by lazy { findNavController(R.id.mainNavHostFragment) }

    override val layoutId = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        setupViews()
    }

    private fun setupViews() {
        bottomNavigation.setupWithNavController(navController)
    }
}
