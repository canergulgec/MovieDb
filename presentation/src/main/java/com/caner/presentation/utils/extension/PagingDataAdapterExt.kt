package com.caner.presentation.utils.extension

import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter

fun PagingDataAdapter<*, *>.withLoadStateAll(
    header: LoadStateAdapter<*>,
    footer: LoadStateAdapter<*>,
    refresh: LoadStateAdapter<*>
): ConcatAdapter {
    addLoadStateListener { loadStates ->
        header.loadState = loadStates.prepend
        footer.loadState = loadStates.append
        refresh.loadState = loadStates.refresh
    }
    return ConcatAdapter(refresh, header, this, footer)
}
