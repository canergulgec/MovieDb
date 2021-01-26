package com.android.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import com.android.data.model.remote.VideoItem

class MovieVideosDiffUtil : DiffUtil.ItemCallback<VideoItem>() {
    override fun areItemsTheSame(
        oldItem: VideoItem,
        newItem: VideoItem
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: VideoItem,
        newItem: VideoItem
    ): Boolean =
        oldItem == newItem
}