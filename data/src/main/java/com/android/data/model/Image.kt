package com.android.data.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(val url: String?) : Parcelable {
    companion object {
        private const val PATH = "https://image.tmdb.org/t/p"
    }

    @IgnoredOnParcel
    val small: String = "$PATH/w92/$url"

    @IgnoredOnParcel
    val medium: String = "$PATH/w185/$url"

    @IgnoredOnParcel
    val large: String = "$PATH/w342/$url"

    @IgnoredOnParcel
    val original: String = "$PATH/original/$url"
}