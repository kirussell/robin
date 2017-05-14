package com.kirussell.robin

import android.app.Application
import android.databinding.DataBindingUtil
import com.kirussell.databindings.adapters.RobinDataBindingComponent
import com.kirussell.robin.di.DaggerRobinComponent
import com.kirussell.robin.di.RobinComponent
import com.kirussell.robin.di.RobinModule
import com.squareup.leakcanary.LeakCanary


class RobinApp : Application() {

    lateinit var robinComponent: RobinComponent

    override fun onCreate() {
        super.onCreate()
        setupLeakCanary()
        setupDatabinding()
        setupDagger()
    }

    private fun setupLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) LeakCanary.install(this)
    }

    private fun setupDatabinding() {
        DataBindingUtil.setDefaultComponent(RobinDataBindingComponent())
    }

    private fun setupDagger() {
        robinComponent = DaggerRobinComponent.builder()
                .robinModule(RobinModule(this))
                .build()
    }
}
