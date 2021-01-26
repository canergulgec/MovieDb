package com.caner.common.ext

import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

fun RecyclerView.init(
    adapter: Adapter<*>,
    itemDecoration: List<RecyclerView.ItemDecoration>? = null,
    rvLayoutManager: RecyclerView.LayoutManager? = null,
    hasFixedSize: Boolean = false
): RecyclerView {
    setHasFixedSize(hasFixedSize)
    itemDecoration?.let { decorationList ->
        decorationList.forEach {
            addItemDecoration(it)
        }
    }
    rvLayoutManager?.let { layoutManager = rvLayoutManager }
    setAdapter(adapter)
    return this
}

fun RecyclerView.initPagerSnapHelper(
    adapter: Adapter<*>,
    itemDecoration: List<RecyclerView.ItemDecoration>? = null,
    rvLayoutManager: RecyclerView.LayoutManager? = null,
    hasFixedSize: Boolean = false
): RecyclerView {
    init(adapter, itemDecoration, rvLayoutManager, hasFixedSize)
    PagerSnapHelper().attachToRecyclerView(this)
    return this
}