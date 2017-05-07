package com.kirussell.databindings.adapters

import android.databinding.BindingAdapter
import android.view.View


class ViewBindingAdapter {

    @BindingAdapter("android:onClick")
    fun adapter(view: View, func: kotlin.Function0<kotlin.Unit>) {
        view.setOnClickListener { func() }
    }

}