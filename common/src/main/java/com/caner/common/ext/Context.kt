package com.caner.common.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build

fun Context.isConnected(): Boolean {
    val cm =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return cm.activeNetwork != null
    } else {
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected = activeNetwork?.isConnectedOrConnecting ?: false
        isConnected
    }
}
