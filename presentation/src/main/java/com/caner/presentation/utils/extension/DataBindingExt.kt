package com.caner.presentation.utils.extension

import androidx.databinding.ViewDataBinding

inline fun <reified T : ViewDataBinding> T.use(crossinline block: T.() -> Unit = {}) {
    block()
    executePendingBindings()
}
