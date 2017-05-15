package com.kirussell.base.adapterdelegates.base

import android.support.v7.widget.RecyclerView


/**
 * This is Adapter that will handle delegation logic
 * Provide [DataAdapter] and [AdapterViewDelegate] objects through constructor
 * This adapter will handle
 */
class DelegationAdapter(
        private val mDataAdapterDelegate: DataAdapter,
        viewDelegates: List<AdapterViewDelegate<RecyclerView.ViewHolder>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mDelegatesManager = AdapterDelegatesManager()

    init {
        for (delegate in viewDelegates) {
            mDelegatesManager.addDelegate(delegate)
        }
        mDataAdapterDelegate.setDataObserver(this)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return mDelegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mDelegatesManager.onBindViewHolder(mDataAdapterDelegate.getItem(position), holder)
    }

    override fun getItemViewType(position: Int): Int {
        return mDelegatesManager.getItemViewType(mDataAdapterDelegate.getItem(position))
    }

    override fun getItemCount(): Int = mDataAdapterDelegate.getItemCount()

    override fun getItemId(position: Int): Long = mDataAdapterDelegate.getItemId(position)

    fun getAdapterViewDelegate(viewType: Int): AdapterViewDelegate<*>? {
        return mDelegatesManager.getDelegateForViewType(viewType)
    }

}