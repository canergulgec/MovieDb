package com.android.presentation.adapter.viewholder

import com.android.base.BaseViewHolder
import com.android.base.ext.loadImage
import com.android.base.ext.use
import com.android.data.model.Image
import com.android.data.model.remote.BackdropItem
import com.android.presentation.databinding.RecyclerviewMovieImageBinding

class MovieImagesViewHolder constructor(
    private val imageBinding: RecyclerviewMovieImageBinding
) : BaseViewHolder<BackdropItem, RecyclerviewMovieImageBinding>(imageBinding) {

    override fun bind() {
        getRowItem()?.apply {
            imageBinding.use {
                val path = Image(getRowItem()?.file_path)
                movieBackdropIv.loadImage(path.original)
            }
        }
    }
}