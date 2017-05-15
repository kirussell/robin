package com.kirussell.databindings.adapters;

import android.databinding.DataBindingComponent;


public class RobinDataBindingComponent implements DataBindingComponent {

    @Override
    public ViewPagerBindingAdapters getViewPagerBindingAdapters() {
        return new ViewPagerBindingAdapters();
    }

    @Override
    public ViewBindingAdapters getViewBindingAdapters() {
        return new ViewBindingAdapters();
    }

    @Override
    public TabLayoutBindingAdapters getTabLayoutBindingAdapters() {
        return new TabLayoutBindingAdapters();
    }

    @Override
    public RecyclerViewBindingAdapters getRecyclerViewBindingAdapters() {
        return new RecyclerViewBindingAdapters();
    }
}
