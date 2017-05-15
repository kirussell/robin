package com.kirussell.databindings.adapters

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.kirussell.base.adapterdelegates.base.AdapterViewDelegate
import com.kirussell.base.adapterdelegates.base.DataAdapter
import com.kirussell.base.adapterdelegates.base.DelegationAdapter


class RecyclerViewBindingAdapters {

    @BindingAdapter("android:adapter")
    fun setupRecyclerViewAdapter(recycler: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recycler.adapter = adapter
    }

    @BindingAdapter("android:viewDelegates", "android:data")
    fun setupRecyclerViewDelegationAdapter(
            recycler: RecyclerView,
            delegates: List<AdapterViewDelegate<RecyclerView.ViewHolder>>,
            data: DataAdapter
    ) {
        recycler.adapter = DelegationAdapter(data, delegates)
    }

    @BindingAdapter("android:layoutManager")
    fun setupLayoutManager(recycler: RecyclerView, adapter: RecyclerView.LayoutManager) {
        recycler.layoutManager = adapter
    }
}