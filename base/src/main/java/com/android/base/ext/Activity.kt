package com.android.base.ext

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.toast(message: String?) =
    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()