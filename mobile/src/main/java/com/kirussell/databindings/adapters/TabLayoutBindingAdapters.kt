package com.kirussell.databindings.adapters

import android.databinding.BindingAdapter
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager


class TabLayoutBindingAdapters {

    @BindingAdapter("android:viewPager")
    fun setupWithViewPager(tabs: TabLayout, viewPager: ViewPager) {
        tabs.setupWithViewPager(viewPager)
    }
}
