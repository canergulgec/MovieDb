package com.android.base

/**
 * Class that hold network state
 */
sealed class NetworkState {
    object Loading : NetworkState()
    object Success : NetworkState()
    object Failed : NetworkState()
}