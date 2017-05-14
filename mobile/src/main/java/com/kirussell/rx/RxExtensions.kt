package com.kirussell.rx

import android.util.Log
import io.reactivex.Observable
import io.reactivex.functions.Consumer

fun <T : Throwable> onErrorDefault() : Consumer<T> = Consumer { t ->
    Log.e("Observable", t.toString())
}

fun <O> Observable<O>.subscribeNoErr(onNext: (O) -> Unit) =
    subscribe(Consumer { o -> onNext.invoke(o) }, onErrorDefault<Throwable>())