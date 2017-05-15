package com.kirussell.robin.curl.di

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kirussell.base.ResourcesProvider
import com.kirussell.base.adapterdelegates.base.AdapterViewDelegate
import com.kirussell.base.adapterdelegates.base.ListDataAdapter
import com.kirussell.base.di.subcomponents.SubcomponentBuilder
import com.kirussell.base.rx.RxLifecycleScopes
import com.kirussell.robin.curl.CurlActionItemViewDelegate
import com.kirussell.robin.curl.CurlActionsListFragment
import com.kirussell.robin.curl.CurlActionsListPresenter
import com.kirussell.robin.curl.CurlPresenter
import com.kirussell.robin.home.di.HomeModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Scope

@CurlScope
@Subcomponent(modules = arrayOf(CurlModule::class))
interface CurlComponent {
    fun inject(fragment: CurlActionsListFragment)

    @Subcomponent.Builder
    interface Builder : SubcomponentBuilder<CurlComponent> {
        fun curlModule(module: CurlModule)
    }
}

@Module(includes = arrayOf(HomeModule.ModuleBinds::class, CurlModule.ModuleBinds::class))
class CurlModule(
        val context: Context,
        val resProvider: ResourcesProvider,
        val rxLifecycleScopes: RxLifecycleScopes
) {

    @Provides @CurlScope fun resourcesProvider(): ResourcesProvider = resProvider
    @Provides @CurlScope fun rxLifecycleScopes(): RxLifecycleScopes = rxLifecycleScopes
    @Provides @CurlScope fun layoutManager(): RecyclerView.LayoutManager
            = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }

    @Provides @CurlScope fun data(): ListDataAdapter = ListDataAdapter()
    @Provides @CurlScope fun viewDelegates(): List<AdapterViewDelegate<*>>
            = arrayListOf(CurlActionItemViewDelegate())

    @Module
    interface ModuleBinds {
        @Binds fun presenter(impl: CurlPresenter): CurlActionsListPresenter
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CurlScope

