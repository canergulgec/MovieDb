package com.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutId, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(savedInstanceState)
    }

    fun setProgressStatus(viewStatus: NetworkState) =
        with(activity) { if (this is BaseActivity) setProgressStatus(viewStatus) }

    abstract fun initView(savedInstanceState: Bundle?)

    @get:LayoutRes
    abstract val layoutId: Int
}

