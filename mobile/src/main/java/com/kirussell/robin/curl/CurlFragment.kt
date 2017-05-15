package com.kirussell.robin.curl

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kirussell.base.BaseFragment
import com.kirussell.base.adapterdelegates.base.AdapterViewDelegate
import com.kirussell.base.adapterdelegates.base.ListDataAdapter
import com.kirussell.robin.R
import com.kirussell.robin.curl.di.CurlComponent
import com.kirussell.robin.curl.di.CurlModule
import com.kirussell.robin.curl.di.CurlScope
import com.kirussell.robin.databinding.FragmentCurlActionsListBinding
import javax.inject.Inject


class CurlActionsListFragment : BaseFragment<FragmentCurlActionsListBinding, CurlComponent, CurlComponent.Builder>() {

    @Inject lateinit var viewState: CurlActionsListViewState
    @Inject lateinit var presenter: CurlActionsListPresenter

    override fun onBuildComponent(builder: CurlComponent.Builder) {
        builder.curlModule(CurlModule(context, this, this))
    }

    override fun fragmentLayoutRes() = R.layout.fragment_curl_actions_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subcomponent(CurlComponent::class.java).inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewState = viewState
    }
}

@CurlScope
class CurlActionsListViewState @Inject constructor(
        val layoutManager: RecyclerView.LayoutManager,
        val actionsList: ListDataAdapter,
        val viewDelegates: List<@JvmSuppressWildcards AdapterViewDelegate<*>>
)

interface CurlActionsListPresenter
