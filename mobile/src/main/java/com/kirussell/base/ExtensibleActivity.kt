package com.kirussell.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


open class ExtensibleActivity : AppCompatActivity() {

    val activityLifecycleExtensions = mutableListOf<ActivityLifecycleCallbacks>()

    fun addLifecycleCallbacks(callbacks: ActivityLifecycleCallbacks) {
        activityLifecycleExtensions.add(callbacks)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLifecycleExtensions.forEach { c -> c.onCreate(savedInstanceState) }
    }

    override fun onStart() {
        super.onStart()
        activityLifecycleExtensions.forEach { c -> c.onStart() }
    }

    override fun onResume() {
        super.onResume()
        activityLifecycleExtensions.forEach { c -> c.onResume() }
    }

    override fun onPause() {
        super.onPause()
        activityLifecycleExtensions.forEach { c -> c.onPause() }
    }

    override fun onStop() {
        super.onStop()
        activityLifecycleExtensions.forEach { c -> c.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityLifecycleExtensions.forEach { c -> c.onDestroy() }
    }
}

interface ActivityLifecycleCallbacks {
    fun onCreate(savedInstanceState: Bundle?): Unit
    fun onStart(): Unit
    fun onResume(): Unit
    fun onPause(): Unit
    fun onStop(): Unit
    fun onDestroy(): Unit
}