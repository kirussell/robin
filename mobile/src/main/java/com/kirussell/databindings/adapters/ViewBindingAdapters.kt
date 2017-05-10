package com.kirussell.databindings.adapters

import android.databinding.BindingAdapter
import android.view.View
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observer


class ViewBindingAdapters {

    @BindingAdapter("android:onClick")
    fun click(view: View, func: kotlin.Function0<kotlin.Unit>) {
        view.setOnClickListener { func() }
    }

    @BindingAdapter("android:onClick")
    fun click(view: View, clickObs: ClickObservable) {
        view.setOnClickListener { clickObs.accept(Click()) }
    }
}

class ClickObservable: Relay<Click>() {

    private val relay = PublishRelay.create<Click>()

    override fun accept(value: Click?) {
        relay.accept(value)
    }

    override fun subscribeActual(observer: Observer<in Click>?) {
        relay.subscribeActual(observer)
    }

    override fun hasObservers(): Boolean {
        return relay.hasObservers()
    }
}

class Click