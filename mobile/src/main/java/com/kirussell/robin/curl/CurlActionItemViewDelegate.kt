package com.kirussell.robin.curl

import com.kirussell.base.adapterdelegates.databindings.DataBindingsViewDelegate
import com.kirussell.robin.R
import com.kirussell.robin.databinding.ItemCurlActionBinding

class CurlActionItemViewDelegate : DataBindingsViewDelegate<ItemCurlActionBinding>() {

    override fun isForViewType(item: Any) = true

    override fun itemLayoutRes(): Int = R.layout.item_curl_action

}

class CurlActionItemViewState(
        val name: String
)
