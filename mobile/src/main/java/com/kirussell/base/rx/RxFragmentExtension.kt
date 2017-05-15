package com.kirussell.base.rx

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kirussell.base.FragmentLifecycleCallbacks

class RxFragmentExtension(val scopes: RxLifecycleScopes) : FragmentLifecycleCallbacks {
    override fun onAttach(context: Context?) {
        /* do nothing */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        /* do nothing */
    }

    override fun onCreateView(
            inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?
    ) {
        /* do nothing */
    }

    override fun onViewCreated(
            view: View?, savedInstanceState: Bundle?
    ) {
        scopes.exists().onScopeBegins()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) { /* do nothing */
    }

    override fun onStart() {
        scopes.displayed().onScopeBegins()
    }

    override fun onResume() {
        scopes.running().onScopeBegins()
    }

    override fun onPause() {
        scopes.running().onScopeEnds()
    }

    override fun onStop() {
        scopes.displayed().onScopeEnds()
    }

    override fun onDestroyView() {
        scopes.exists().onScopeEnds()
    }

    override fun onDestroy() {
        /* do nothing */
    }

    override fun onDetach() {
        /* do nothing */
    }

}