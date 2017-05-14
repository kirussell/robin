package com.kirussell.base.adapterdelegates

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


internal class AdapterDelegatesManager {

    private val delegates = SparseArrayCompat<AdapterViewDelegate<*>>()

    fun addDelegate(delegate: AdapterViewDelegate<*>): AdapterDelegatesManager {
        var viewType = delegates.size()
        while (delegates.get(viewType) != null) {
            viewType++
        }
        return addDelegate(viewType, delegate)
    }

    private fun addDelegate(
            viewType: Int, delegate: AdapterViewDelegate<*>
    ): AdapterDelegatesManager {
        if (delegates.get(viewType) != null) {
            throw IllegalArgumentException("An AdapterDelegate is already registered for the " +
                    "viewType = $viewType. Already registered AdapterDelegate " +
                    "is ${delegates.get(viewType)})")
        }
        delegates.put(viewType, delegate)
        return this
    }

    fun getItemViewType(item: Any): Int {
        val delegatesCount = delegates.size()
        for (i in 0..delegatesCount - 1) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(item)) {
                return delegates.keyAt(i)
            }
        }
        throw NullPointerException("No AdapterDelegate added that matches $item in data source")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegate = getDelegateForViewType(viewType)
        delegate ?: throw NullPointerException("No AdapterDelegate added for ViewType " + viewType)
        return delegate.onCreateViewHolder(parent)
    }

    fun <VH : RecyclerView.ViewHolder> onBindViewHolder(item: Any, viewHolder: VH) {
        val delegate = getDelegateForViewType(viewHolder.itemViewType)
        delegate ?: throw NullPointerException("No delegate found for item at position = $item for " +
                "viewType = $viewHolder.itemViewType")
        delegate.onBindViewHolder(item, viewHolder)
    }

    fun getDelegateForViewType(viewType: Int): AdapterViewDelegate<*>? = delegates.get(viewType)

}