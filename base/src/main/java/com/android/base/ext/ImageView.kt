package com.android.base.ext

import android.widget.ImageView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation

fun ImageView.loadImage(path: String?) {
    load(path) {
        crossfade(true)
    }
}

fun ImageView.loadRoundedCornerImage(path: String?, radius: Float) {
    load(path) {
        transformations(RoundedCornersTransformation(radius))
    }
}

fun ImageView.loadBlurImage(path: String?, blurValue: Float) {
    load(path) {
        transformations(BlurTransformation(context, blurValue))
    }
}