package com.kirussell.base.adapterdelegates

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


abstract class AdapterViewDelegate<out VH : RecyclerView.ViewHolder> {

    abstract fun isForViewType(item: Any): Boolean

    abstract fun onCreateViewHolder(parent: ViewGroup): VH

    abstract fun onBindViewHolder(item: Any, holder: RecyclerView.ViewHolder)
}
