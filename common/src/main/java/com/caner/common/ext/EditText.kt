package com.caner.common.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

inline fun EditText.afterTextChanged(crossinline afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged(editable.toString())
        }
    })
}