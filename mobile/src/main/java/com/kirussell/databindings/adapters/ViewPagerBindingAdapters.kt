package com.kirussell.databindings.adapters

import android.databinding.BindingAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager


class ViewPagerBindingAdapters {

    @BindingAdapter("android:adapter")
    fun adapter(view: ViewPager, adapter: PagerAdapter) {
        view.adapter = adapter
    }

}