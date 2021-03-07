package com.caner.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.caner.common.R

@BindingAdapter(value = ["bind:path", "bind:radius"])
fun ImageView.loadRoundedCornerImage(path: String?, radius: Float) {
    load(path) {
        placeholder(R.drawable.image_placeholder)
        error(R.drawable.no_image)
        transformations(RoundedCornersTransformation(radius))
    }
}

@BindingAdapter("image")
fun ImageView.loadImage(path: String?) {
    load(path) {
        placeholder(R.drawable.image_placeholder)
        error(R.drawable.no_image)
    }
}