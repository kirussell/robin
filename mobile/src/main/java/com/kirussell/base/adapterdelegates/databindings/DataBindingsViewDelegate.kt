package com.kirussell.base.adapterdelegates.databindings

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kirussell.base.adapterdelegates.base.AdapterViewDelegate
import com.kirussell.robin.BR


abstract class DataBindingsViewDelegate<DataBinding : ViewDataBinding>
    : AdapterViewDelegate<RecyclerView.ViewHolder>() {

    lateinit var dataBinding: DataBinding

    override fun onCreateViewHolder(parent: ViewGroup): DataBindingsViewHolder {
        dataBinding = DataBindingUtil.inflate<DataBinding>(
                LayoutInflater.from(parent.context), itemLayoutRes(), parent, false
        )
        return DataBindingsViewHolder(dataBinding.root)
    }

    @LayoutRes abstract fun itemLayoutRes(): Int

    override fun onBindViewHolder(item: Any, holder: RecyclerView.ViewHolder) {
        dataBinding.setVariable(BR.viewState, item)
    }

    class DataBindingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

