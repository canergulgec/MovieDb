package com.android.moviedb.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.base.BaseActivity
import com.android.moviedb.R
import com.android.moviedb.databinding.ActivityMainBinding
import com.caner.common.Constants
import com.caner.common.ext.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController by lazy { findNavController(R.id.mainNavHostFragment) }

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        when (destination.id) {
            R.id.movieDetailFragment -> binding.bottomNavigation.visible(false)
            else -> binding.bottomNavigation.visible(true)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            AppCompatDelegate.setDefaultNightMode(savedInstanceState.getInt(Constants.NIGHT_MODE))
        }

        setupViews()
        binding.dayNightBtn.setOnClickListener {
            setDayAndNight()
        }
    }

    private fun setupViews() {
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun setDayAndNight() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Configuration.UI_MODE_NIGHT_NO ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val nightMode = AppCompatDelegate.getDefaultNightMode()
        outState.putInt(Constants.NIGHT_MODE, nightMode)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
}