package com.kirussell.base

import com.kirussell.base.di.subcomponents.SubcomponentBuilder
import com.kirussell.base.rx.LifecycleScope
import com.kirussell.base.rx.RxActivityExtension
import com.kirussell.base.rx.RxLifecycleScopes
import com.kirussell.base.rx.SimpleRxLifecycleScopes
import com.kirussell.robin.di.robinComponent


abstract class BaseActivity<Component, in ComponentBuilder : SubcomponentBuilder<Component>>
    : ExtensibleActivity(), RxLifecycleScopes by SimpleRxLifecycleScopes() {

    private val scopes = SimpleRxLifecycleScopes()

    init {
        addLifecycleCallbacks(RxActivityExtension(scopes))
    }

    override fun exists(action: LifecycleScope.() -> Unit) = scopes.exists(action)

    override fun displayed(action: LifecycleScope.() -> Unit) = scopes.displayed(action)

    override fun running(action: LifecycleScope.() -> Unit) = scopes.running(action)

    override fun exists() = scopes.exists()

    override fun displayed() = scopes.displayed()

    override fun running() = scopes.running()

    fun subcomponent(componentClass: Class<Component>): Component {
        val subcomponentBuilder = robinComponent().subcomponents()[componentClass]
        subcomponentBuilder ?: throw IllegalArgumentException(
                "Can not get component with given key=$componentClass"
        )
        @Suppress("UNCHECKED_CAST")
        val builder = subcomponentBuilder as ComponentBuilder
        onBuildComponent(builder)
        return subcomponentBuilder.build()
    }

    abstract fun onBuildComponent(builder: ComponentBuilder)


}