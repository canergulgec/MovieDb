package com.caner.presentation.utils.extension

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
