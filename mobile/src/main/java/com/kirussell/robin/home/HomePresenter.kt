package com.kirussell.robin.home

import com.kirussell.base.ResourcesProvider
import com.kirussell.robin.R
import com.kirussell.robin.home.di.HomeScope
import com.kirussell.rx.subscribeNoErr
import javax.inject.Inject


@HomeScope
class HomePagesPresenter internal @Inject constructor(
        val viewState: HomeViewState,
        val resourceProvider: ResourcesProvider
): HomePresenter, ResourcesProvider by resourceProvider {

    init {
        viewState.observeFabClick()
                .subscribeNoErr { _ -> onStartCommandCreation()}
        viewState.adapter.data.put(
                HomeViewState.CURL_HELPER_TAB, getString(R.string.curl_helper)
        )
    }

    private fun onStartCommandCreation() {
        //TODO
    }
}
