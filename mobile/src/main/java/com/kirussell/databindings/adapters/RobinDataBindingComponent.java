package com.kirussell.databindings.adapters;

import android.databinding.DataBindingComponent;


public class RobinDataBindingComponent implements DataBindingComponent {

    @Override
    public ViewPagerBindingAdapters getViewPagerBindingAdapters() {
        return new ViewPagerBindingAdapters();
    }

    @Override
    public ViewBindingAdapter getViewBindingAdapter() {
        return new ViewBindingAdapter();
    }
}
