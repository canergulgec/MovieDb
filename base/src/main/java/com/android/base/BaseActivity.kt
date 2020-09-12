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

    fun setProgressStatus(show: Boolean) {
        when (show) {
            true -> if (!progressDialog.isShowing) progressDialog.show()
            false -> if (progressDialog.isShowing) progressDialog.dismiss()
        }
    }

    abstract fun initView(savedInstanceState: Bundle?)

    @get:LayoutRes
    abstract val layoutId: Int
}