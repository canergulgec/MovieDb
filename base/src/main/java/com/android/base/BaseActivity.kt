package com.android.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.caner.common.widget.CustomProgressDialog

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindLayout: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindLayout.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        progressDialog = CustomProgressDialog(
            this,
            R.style.ProgressDialogStyle
        )

        initView(savedInstanceState)
    }

    fun showLoading(isVisible: Boolean) {
        when (isVisible) {
            true -> if (!progressDialog.isShowing) progressDialog.show()
            false -> if (progressDialog.isShowing) progressDialog.dismiss()
        }
    }

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}