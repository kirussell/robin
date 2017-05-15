package com.kirussell.robin.home

import android.content.Context
import android.widget.Toast
import com.kirussell.base.ResourcesProvider
import com.kirussell.base.rx.RxLifecycleScopes
import com.kirussell.robin.R
import com.kirussell.robin.home.di.HomeScope
import com.kirussell.rx.subscribeNoErr
import javax.inject.Inject


@HomeScope
class HomePagesPresenter internal @Inject constructor(
        val viewState: HomeViewState,
        val resourceProvider: ResourcesProvider,
        val rxLifecycleScopes: RxLifecycleScopes,
        val ctx: Context
) : HomePresenter, ResourcesProvider by resourceProvider, RxLifecycleScopes by rxLifecycleScopes {

    init {
        viewState.adapter.data.put(
                HomeViewState.CURL_HELPER_TAB, getString(R.string.curl_helper)
        )

        running {
            viewState.fabClick().observe()
                    .compose(withinScope())
                    .subscribeNoErr { _ -> onStartCommandCreation() }
        }

        displayed {
            viewState.pageChange().observe()
                    .compose(withinScope())
                    .subscribeNoErr { pagePosition ->
                        viewState.fabVisible.set(when (pagePosition) {
                            HomeViewState.CURL_HELPER_TAB -> true
                            else -> false
                        })
                    }
        }
    }

    private fun onStartCommandCreation() {
        Toast.makeText(ctx, "OLOLO", Toast.LENGTH_LONG).show()
    }
}
