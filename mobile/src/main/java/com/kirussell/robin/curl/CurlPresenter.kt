package com.kirussell.robin.curl

import com.kirussell.base.adapterdelegates.base.ListDataAdapter
import com.kirussell.robin.curl.di.CurlScope
import javax.inject.Inject


@CurlScope
class CurlPresenter @Inject constructor(
        val viewState: CurlActionsListViewState,
        val data: ListDataAdapter
) : CurlActionsListPresenter {

    init {
        data.addItem(CurlActionItemViewState("Curl Action 1"))
        data.addItem(CurlActionItemViewState("Curl Action 2"))
    }
}