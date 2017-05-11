package com.kirussell.robin

import com.kirussell.base.ResourcesProvider
import com.kirussell.rx.subscribeNoErr


class HomePagesPresenter internal constructor(
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
