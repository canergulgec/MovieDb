package com.caner.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

@BindingAdapter(value = ["bind:path", "bind:radius"])
fun ImageView.loadRoundedCornerImage(path: String?, radius: Float) {
    load(path) {
        transformations(RoundedCornersTransformation(radius))
    }
}

@BindingAdapter("image")
fun ImageView.loadImage(path: String?) {
    load(path)
}