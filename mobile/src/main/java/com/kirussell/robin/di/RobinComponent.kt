package com.kirussell.robin.di

import android.app.Activity
import android.content.Context
import com.kirussell.base.di.subcomponents.SubcomponentBuilder
import com.kirussell.robin.RobinApp

import dagger.Component
import dagger.Module
import dagger.Provides


@Component(modules = arrayOf(SubcomponentsModule::class, RobinModule::class))
interface RobinComponent {
    fun subcomponents(): Map<Class<*>, @JvmSuppressWildcards SubcomponentBuilder<*>>
}

@Module
class RobinModule(val context: Context) {
    @Provides fun context() = context
}

fun Activity.robinComponent(): RobinComponent {
    if (applicationContext is RobinApp) {
        return (applicationContext as RobinApp).robinComponent
    }
    throw IllegalArgumentException("Can not get component from given Context $applicationContext")
}