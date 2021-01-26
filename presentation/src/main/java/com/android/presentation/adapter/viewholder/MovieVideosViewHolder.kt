package com.android.presentation.adapter.viewholder

import com.android.base.BaseViewHolder
import com.caner.common.ext.use
import com.android.data.model.remote.VideoItem
import com.android.presentation.databinding.RecyclerviewMovieVideoBinding

class MovieVideosViewHolder constructor(
    private val videoBinding: RecyclerviewMovieVideoBinding
) : BaseViewHolder<VideoItem, RecyclerviewMovieVideoBinding>(videoBinding) {

    override fun bind() {
        getRowItem()?.apply {
            videoBinding.use {
                video = getRowItem()
            }
        }
    }
}