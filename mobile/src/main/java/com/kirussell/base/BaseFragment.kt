package com.kirussell.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kirussell.base.di.subcomponents.SubcomponentBuilder
import com.kirussell.base.rx.LifecycleScope
import com.kirussell.base.rx.RxFragmentExtension
import com.kirussell.base.rx.RxLifecycleScopes
import com.kirussell.base.rx.SimpleRxLifecycleScopes
import com.kirussell.robin.di.robinComponent


abstract class BaseFragment<DataBinding : ViewDataBinding, Component, in ComponentBuilder : SubcomponentBuilder<Component>>
    : ExtensibleFragment(), RxLifecycleScopes, ResourcesProvider {

    private val scopes = SimpleRxLifecycleScopes()
    protected lateinit var dataBinding: DataBinding

    init {
        addLifecycleCallbacks(RxFragmentExtension(scopes))
    }

    override fun exists(action: LifecycleScope.() -> Unit) = scopes.exists(action)

    override fun displayed(action: LifecycleScope.() -> Unit) = scopes.displayed(action)

    override fun running(action: LifecycleScope.() -> Unit) = scopes.running(action)

    override fun exists() = scopes.exists()

    override fun displayed() = scopes.displayed()

    override fun running() = scopes.running()

    fun subcomponent(componentClass: Class<Component>): Component {
        val subcomponentBuilder = activity.robinComponent().subcomponents()[componentClass]
        subcomponentBuilder ?: throw IllegalArgumentException(
                "Can not get component with given key=$componentClass"
        )
        @Suppress("UNCHECKED_CAST")
        val builder = subcomponentBuilder as ComponentBuilder
        onBuildComponent(builder)
        return subcomponentBuilder.build()
    }

    abstract fun onBuildComponent(builder: ComponentBuilder)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding = DataBindingUtil.inflate<DataBinding>(inflater, fragmentLayoutRes(), container, false)
        return dataBinding.root
    }

    @LayoutRes abstract fun fragmentLayoutRes(): Int
}