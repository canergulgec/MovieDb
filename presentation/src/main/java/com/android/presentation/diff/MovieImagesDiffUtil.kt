package com.android.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import com.android.data.model.remote.BackdropItem

class MovieImagesDiffUtil : DiffUtil.ItemCallback<BackdropItem>() {
    override fun areItemsTheSame(
        oldItem: BackdropItem,
        newItem: BackdropItem
    ): Boolean =
        false

    override fun areContentsTheSame(
        oldItem: BackdropItem,
        newItem: BackdropItem
    ): Boolean =
        oldItem == newItem
}