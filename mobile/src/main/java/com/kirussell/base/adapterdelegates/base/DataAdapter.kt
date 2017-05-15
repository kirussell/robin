package com.kirussell.base.adapterdelegates.base

import android.support.v7.widget.RecyclerView


abstract class DataAdapter {

    private var mAdapter: RecyclerView.Adapter<*>? = null

    abstract fun getItem(position: Int): Any

    abstract fun getItemCount(): Int

    abstract fun getItemId(position: Int): Long

    fun notifyDataSetChanged() {
        mAdapter?.notifyDataSetChanged()
    }

    fun notifyItemChanged(position: Int) {
        mAdapter?.notifyItemChanged(position)
    }

    internal fun setDataObserver(adapter: RecyclerView.Adapter<*>) {
        mAdapter = adapter
    }

}

class ListDataAdapter : DataAdapter() {

    private val mItems = mutableListOf<Any>()

    fun setItems(items: List<Any>) {
        mItems.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    fun addItem(item: Any) {
        val size = mItems.size
        mItems.add(item)
        notifyItemChanged(size)
    }

    fun clear() {
        mItems.clear()
        notifyDataSetChanged()
    }

    fun addItem(item: Any, position: Int) {
        mItems.add(position, item)
        notifyItemChanged(position)
    }

    override fun getItem(position: Int) = mItems[position]

    override fun getItemCount() = mItems.size

    override fun getItemId(position: Int) = position.toLong()
}