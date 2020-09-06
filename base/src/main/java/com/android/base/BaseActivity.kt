package com.android.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.android.base.widget.CustomProgressDialog

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        progressDialog = CustomProgressDialog(
            this,
            R.style.ProgressDialogStyle
        )

        initView(savedInstanceState)
    }

    fun setProgressStatus(state: NetworkState) {
        when (state) {
            NetworkState.Loading -> if (!progressDialog.isShowing) progressDialog.show()
            NetworkState.Success, NetworkState.Failed -> if (progressDialog.isShowing) progressDialog.dismiss()
        }
    }

    abstract fun initView(savedInstanceState: Bundle?)

    @get:LayoutRes
    abstract val layoutId: Int
}