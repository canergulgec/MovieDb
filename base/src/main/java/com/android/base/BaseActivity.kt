package com.android.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.caner.common.widget.CustomProgressDialog

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

    fun setLoadingStatus(isVisible: Boolean) {
        when (isVisible) {
            true -> if (!progressDialog.isShowing) progressDialog.show()
            false -> if (progressDialog.isShowing) progressDialog.dismiss()
        }
    }

    abstract fun initView(savedInstanceState: Bundle?)

    @get:LayoutRes
    abstract val layoutId: Int
}