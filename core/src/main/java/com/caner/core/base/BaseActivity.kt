package com.caner.core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.caner.core.R
import com.caner.core.widget.CustomProgressDialog

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindLayout: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    private val progressDialog by lazy { CustomProgressDialog(this, R.style.ProgressDialogStyle) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindLayout.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
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
