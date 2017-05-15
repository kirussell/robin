package com.kirussell.databindings.adapters

import android.databinding.BindingAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class ViewPagerBindingAdapters {

    @BindingAdapter("android:adapter")
    fun adapter(view: ViewPager, adapter: PagerAdapter) {
        view.adapter = adapter
    }

    @BindingAdapter("android:onPageChangeListener")
    fun onPageChangeListener(view: ViewPager, changeObs: OnPageChangeObservable) {
        view.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                changeObs.acceptSelectedPage(position)
            }
        })
    }
}

class OnPageChangeObservable {

    val subject: PublishSubject<Int> = PublishSubject.create<Int>()

    internal fun acceptSelectedPage(pagePosition: Int) {
        subject.onNext(pagePosition)
    }

    fun observe(): Observable<Int> {
        return subject
    }
}