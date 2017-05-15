package com.kirussell.robin.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import com.kirussell.base.BaseActivity
import com.kirussell.base.ResourcesProvider
import com.kirussell.databindings.adapters.ClickObservable
import com.kirussell.robin.R
import com.kirussell.robin.curl.CurlActionsListFragment
import com.kirussell.robin.databinding.ActivityHomeBinding
import com.kirussell.robin.home.di.HomeComponent
import com.kirussell.robin.home.di.HomeModule
import com.kirussell.robin.home.di.HomeScope
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeComponent, HomeComponent.Builder>(), ResourcesProvider {

    @Inject lateinit var homeViewState: HomeViewState
    @Inject lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subcomponent(HomeComponent::class.java).inject(this)
        DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home).apply {
            viewState = homeViewState
            setSupportActionBar(toolbar)
        }
    }

    override fun onBuildComponent(builder: HomeComponent.Builder) {
        builder.homeModule(HomeModule(supportFragmentManager, this, this))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

@HomeScope
class SectionsPagerAdapter @Inject constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    internal val data = HashMap<Int, String>()

    override fun getItem(position: Int) = when (position) {
        HomeViewState.CURL_HELPER_TAB -> CurlActionsListFragment()
        else -> throw IllegalArgumentException("Have no view for position=$position")
    }

    override fun getCount() = 1

    override fun getPageTitle(position: Int) = when (position) {
        HomeViewState.CURL_HELPER_TAB -> data[HomeViewState.CURL_HELPER_TAB]
        else -> throw IllegalArgumentException("Have no title for position=$position")
    }
}

@HomeScope
class HomeViewState @Inject constructor(
        val adapter: SectionsPagerAdapter
) {
    internal companion object {
        const val CURL_HELPER_TAB = 0
    }

    private val clickObservable = ClickObservable()

    fun fabClick() = clickObservable
}

interface HomePresenter
