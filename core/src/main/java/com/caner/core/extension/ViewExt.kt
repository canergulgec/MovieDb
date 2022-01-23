package com.caner.core.extension

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager

inline var View.isInvisible: Boolean
    get() = visibility == INVISIBLE
    set(value) {
        visibility = if (value) INVISIBLE else VISIBLE
    }

fun View.visible(isShowing: Boolean) {
    visibility = when (isShowing) {
        true -> VISIBLE
        false -> GONE
    }
}

fun View.hideKeyBoard() {
    val inputMethodManager =
        context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}
