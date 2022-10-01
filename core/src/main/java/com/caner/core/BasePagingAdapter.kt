package com.caner.core

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

abstract class BasePagingAdapter<M : Any, DB : ViewBinding, VH : BaseViewHolder<M, DB>>
constructor(itemComparator: DiffUtil.ItemCallback<M>) :
    PagingDataAdapter<M, VH>(itemComparator) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBindings(getItem(position))
        holder.bind()
    }
}
