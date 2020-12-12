package com.caner.common.ext

import android.widget.ImageView
import coil.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation

fun ImageView.loadImage(path: String?) {
    load(path)
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