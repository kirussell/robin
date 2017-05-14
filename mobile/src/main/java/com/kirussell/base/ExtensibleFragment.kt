package com.kirussell.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


open class ExtensibleFragment : Fragment() {

    val fragmentLifecycleExtensions = mutableListOf<FragmentLifecycleCallbacks>()

    fun addLifecycleCallbacks(callbacks: FragmentLifecycleCallbacks) {
        fragmentLifecycleExtensions.add(callbacks)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        fragmentLifecycleExtensions.forEach { c -> c.onAttach(context) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentLifecycleExtensions.forEach { c -> c.onCreate(savedInstanceState) }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        fragmentLifecycleExtensions.forEach { c -> c.onCreateView(inflater, container, savedInstanceState) }
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLifecycleExtensions.forEach { c -> c.onViewCreated(view, savedInstanceState) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentLifecycleExtensions.forEach { c -> c.onActivityCreated(savedInstanceState) }
    }

    override fun onStart() {
        super.onStart()
        fragmentLifecycleExtensions.forEach { c -> c.onStart() }
    }

    override fun onResume() {
        super.onResume()
        fragmentLifecycleExtensions.forEach { c -> c.onResume() }
    }

    override fun onPause() {
        super.onPause()
        fragmentLifecycleExtensions.forEach { c -> c.onPause() }
    }

    override fun onStop() {
        super.onStop()
        fragmentLifecycleExtensions.forEach { c -> c.onStop() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentLifecycleExtensions.forEach { c -> c.onDestroyView() }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentLifecycleExtensions.forEach { c -> c.onDestroy() }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentLifecycleExtensions.forEach { c -> c.onDetach() }
    }
}

interface FragmentLifecycleCallbacks {
    fun onAttach(context: Context?)
    fun onCreate(savedInstanceState: Bundle?): Unit
    fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
    fun onViewCreated(view: View?, savedInstanceState: Bundle?)
    fun onActivityCreated(savedInstanceState: Bundle?)
    fun onStart(): Unit
    fun onResume(): Unit
    fun onPause(): Unit
    fun onStop(): Unit
    fun onDestroyView(): Unit
    fun onDestroy(): Unit
    fun onDetach(): Unit
}