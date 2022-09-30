package com.caner.moviedb.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.caner.core.constants.Constants
import com.caner.core.base.BaseActivity
import com.caner.core.extension.visible
import com.caner.navigation.NavigationDispatcher
import com.caner.moviedb.R
import com.caner.moviedb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var navigationDispatcher: NavigationDispatcher

    private lateinit var navHostFragment: NavHostFragment
    private val navController by lazy { navHostFragment.navController }

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        when (destination.id) {
            R.id.movieDetailFragment -> binding.bottomNavigation.visible(false)
            else -> binding.bottomNavigation.visible(true)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment

        if (savedInstanceState != null) {
            AppCompatDelegate.setDefaultNightMode(savedInstanceState.getInt(Constants.NIGHT_MODE))
        }
        setupBottomNavigation()
        attachNavigationFlow()

        binding.dayNightBtn.setOnClickListener {
            setDayAndNight()
        }
    }

    private fun attachNavigationFlow() {
        navigationDispatcher.navigationCommands.flowWithLifecycle(
            minActiveState = Lifecycle.State.CREATED, lifecycle = lifecycle
        ).onEach { command ->
            command.invoke(Navigation.findNavController(this, R.id.mainNavHostFragment))
        }.launchIn(lifecycleScope)
    }

    private fun setupBottomNavigation() {
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
        get() = {
            installSplashScreen()
            ActivityMainBinding.inflate(it)
        }
}
