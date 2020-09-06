package com.android.base.ext

import androidx.databinding.ViewDataBinding

inline fun <reified T : ViewDataBinding> T.use(crossinline block: T.() -> Unit = {}) {
    block()
    executePendingBindings()
}