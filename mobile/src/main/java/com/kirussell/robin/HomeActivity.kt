package com.kirussell.robin

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kirussell.databindings.adapters.ClickObservable

import com.kirussell.databindings.adapters.RobinDataBindingComponent
import com.kirussell.robin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setDefaultComponent(RobinDataBindingComponent())
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        val homeViewState = HomeViewState(
                SectionsPagerAdapter(supportFragmentManager)
        )
        binding.viewState = homeViewState
        setSupportActionBar(binding.toolbar)

        homeViewState.observeFabClick()
                .subscribe(
                        { Toast.makeText(applicationContext, "OLOLO", Toast.LENGTH_LONG).show() }
                )
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

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int) = when (position) {
            0 -> Fragment()
            else -> throw IllegalArgumentException("Have no view for position=$position")
        }

        override fun getCount() = 1

        override fun getPageTitle(position: Int) = when (position) {
            0 -> "SECTION 1"
            else -> null
        }
    }
}

internal class HomeViewState(
        val adapter: HomeActivity.SectionsPagerAdapter
) {
    private val clickObservable = ClickObservable()

    fun observeFabClick() = clickObservable
}
