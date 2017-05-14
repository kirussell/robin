package com.kirussell.base.rx

import android.os.Bundle
import com.kirussell.base.LifecycleCallbacks


class RxActivityExtension(val scopes: RxLifecycleScopes) : LifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        scopes.exists().onScopeBegins()
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

    override fun onDestroy() {
        scopes.exists().onScopeEnds()
    }
}