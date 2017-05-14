package com.kirussell.databindings.adapters

import android.databinding.BindingAdapter
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class ViewBindingAdapters {

    @BindingAdapter("android:onClick")
    fun click(view: View, func: kotlin.Function0<kotlin.Unit>) {
        view.setOnClickListener { func() }
    }

    @BindingAdapter("android:onClick")
    fun click(view: View, clickObs: ClickObservable) {
        view.setOnClickListener { clickObs.acceptClick(Click()) }
    }
}

class ClickObservable {

    val subject: PublishSubject<Click> = PublishSubject.create<Click>()

    internal fun acceptClick(click: Click?) {
        subject.onNext(click)
    }

    fun observe(): Observable<Click> {
        return subject
    }
}

class Click