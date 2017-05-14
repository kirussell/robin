package com.kirussell.base

import android.support.v7.app.AppCompatActivity
import com.kirussell.base.di.subcomponents.SubcomponentBuilder
import com.kirussell.robin.di.robinComponent


abstract class BaseActivity<Component, in ComponentBuilder : SubcomponentBuilder<Component>> : AppCompatActivity() {

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