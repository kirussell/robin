package com.kirussell.robin

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.kirussell.base.ResourcesProvider
import com.kirussell.databindings.adapters.ClickObservable

import com.kirussell.databindings.adapters.RobinDataBindingComponent
import com.kirussell.robin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), ResourcesProvider {

    private val homeViewState = HomeViewState(
            SectionsPagerAdapter(supportFragmentManager)
    )
    private val presenter = HomePagesPresenter(homeViewState, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setDefaultComponent(RobinDataBindingComponent())
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        binding.viewState = homeViewState
        setSupportActionBar(binding.toolbar)
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

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        internal val data = HashMap<Int, String>()

        override fun getItem(position: Int) = when (position) {
            HomeViewState.CURL_HELPER_TAB -> Fragment() //TODO
            else -> throw IllegalArgumentException("Have no view for position=$position")
        }

        override fun getCount() = 1

        override fun getPageTitle(position: Int) = when (position) {
            HomeViewState.CURL_HELPER_TAB -> data[HomeViewState.CURL_HELPER_TAB]
            else -> throw IllegalArgumentException("Have no title for position=$position")
        }
    }
}

internal class HomeViewState(
        val adapter: HomeActivity.SectionsPagerAdapter
) {
    internal companion object {
        const val CURL_HELPER_TAB = 0
    }

    private val clickObservable = ClickObservable()

    fun observeFabClick() = clickObservable
}

interface HomePresenter
