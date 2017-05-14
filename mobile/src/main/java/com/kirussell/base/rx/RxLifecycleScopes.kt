package com.kirussell.base.rx

import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Publisher
import org.reactivestreams.Subscription
import java.util.concurrent.atomic.AtomicBoolean


class LifecycleScope {

    internal val compositeDisposable = CompositeDisposable()
    private val isOutsideScope = AtomicBoolean(false)
    private val actions = mutableListOf<() -> Unit>()

    internal fun onScopeBegins() {
        isOutsideScope.set(false)
        executeBeginActions()
    }

    internal fun onScopeEnds() {
        if (!isOutsideScope.get()) {
            synchronized(this) {
                compositeDisposable.apply { if (!isDisposed) clear() }
            }
            isOutsideScope.set(true)
        }
    }

    internal fun beginInScope(run: () -> Unit) {
        if (!isOutsideScope.get()) {
            executeBeginActions()
        }
        actions.add(run)
    }

    private fun executeBeginActions() {
        actions.forEach { action ->
            action()
        }
    }

    fun <T> withinScope(): GeneralTransformer<T> = GeneralTransformer(this)
}

interface RxLifecycleScopes {
    fun exists(action: LifecycleScope.() -> Unit): LifecycleScope
    fun displayed(action: LifecycleScope.() -> Unit): LifecycleScope
    fun running(action: LifecycleScope.() -> Unit): LifecycleScope
    fun exists(): LifecycleScope
    fun displayed(): LifecycleScope
    fun running(): LifecycleScope
}

class SimpleRxLifecycleScopes : RxLifecycleScopes {
    private val exists = LifecycleScope()
    private val displayed = LifecycleScope()
    private val running = LifecycleScope()

    override fun exists(action: LifecycleScope.() -> Unit) = exists.apply { beginInScope { action() } }
    override fun displayed(action: LifecycleScope.() -> Unit) = displayed.apply { beginInScope { action() } }
    override fun running(action: LifecycleScope.() -> Unit) = running.apply { beginInScope { action() } }
    override fun exists() = exists
    override fun displayed() = displayed
    override fun running() = running
}

class GeneralTransformer<T> internal constructor(private val scopes: LifecycleScope) : ObservableTransformer<T, T>, SingleTransformer<T, T>,
        MaybeTransformer<T, T>, FlowableTransformer<T, T>, CompletableTransformer {

    override fun apply(upstream: Observable<T>?): ObservableSource<T>? {
        val holder = Holder()
        return upstream?.doOnSubscribe { d ->
            synchronized(holder) {
                holder.disposable = d
                holder.disposable?.apply { scopes.compositeDisposable.add(this) }
            }
        }?.doOnDispose {
            synchronized(holder) {
                holder.disposable?.apply { scopes.compositeDisposable.remove(holder.disposable) }
            }
        }
    }

    override fun apply(upstream: Single<T>?): SingleSource<T>? {
        val holder = Holder()
        return upstream?.doOnSubscribe { d ->
            synchronized(holder) {
                holder.disposable = d
                holder.disposable?.apply { scopes.compositeDisposable.add(this) }
            }
        }?.doOnDispose {
            synchronized(holder) {
                holder.disposable?.apply { scopes.compositeDisposable.remove(holder.disposable) }
            }
        }
    }

    override fun apply(upstream: Maybe<T>?): MaybeSource<T>? {
        val holder = Holder()
        return upstream?.doOnSubscribe { d ->
            synchronized(holder) {
                holder.disposable = d
                holder.disposable?.apply { scopes.compositeDisposable.add(this) }
            }
        }?.doOnDispose {
            synchronized(holder) {
                holder.disposable?.apply { scopes.compositeDisposable.remove(holder.disposable) }
            }
        }
    }

    override fun apply(upstream: Flowable<T>?): Publisher<T>? {
        val holder = Holder()

        return upstream?.doOnSubscribe { d ->
            synchronized(holder) {
                holder.disposable = WrapCancelDisposable(d)
                holder.disposable?.apply { scopes.compositeDisposable.add(this) }
            }
        }?.doOnCancel {
            synchronized(holder) {
                holder.disposable?.apply { scopes.compositeDisposable.remove(holder.disposable) }
            }
        }
    }

    override fun apply(upstream: Completable?): CompletableSource? {
        val holder = Holder()
        return upstream?.doOnSubscribe { d ->
            synchronized(holder) {
                holder.disposable = d
                holder.disposable?.apply { scopes.compositeDisposable.add(this) }
            }
        }?.doOnDispose {
            synchronized(holder) {
                holder.disposable?.apply { scopes.compositeDisposable.remove(holder.disposable) }
            }
        }
    }

    inner class Holder {
        var disposable: Disposable? = null
    }

    inner class WrapCancelDisposable(private var sub: Subscription?) : Disposable {
        override fun isDisposed() = sub == null

        override fun dispose() {
            sub?.cancel()
            sub = null
        }
    }
}
