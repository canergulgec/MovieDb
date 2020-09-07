package com.android.moviedb.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.android.moviedb.R
import com.android.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    /**
     * Using [Provider] to enable lazy retrieval of the [NavController]
     * The id used to get the [NavController] instance will be looked up
     * before the call to setContentView cos in the [Hilt_MainActivity],
     * inject() is called before super.onCreate(), which is normal.
     *
     * Using [Provider] or [Lazy] will prevent it from throwing an error,
     * since they offer lazy retrieval and or initialization.
     */
    @Inject
    lateinit var navControllerProvider: Provider<NavController>

    private val navController: NavController
        get() = navControllerProvider.get()

    override val layoutId = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        setupViews()
    }

    private fun setupViews() {
        bottomNavigation.setupWithNavController(navController)
    }
}
