package com.kirussell.robin.home.di

import android.support.v4.app.FragmentManager
import com.kirussell.base.ResourcesProvider
import com.kirussell.base.di.subcomponents.SubcomponentBuilder
import com.kirussell.robin.home.HomeActivity
import com.kirussell.robin.home.HomePagesPresenter
import com.kirussell.robin.home.HomePresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Scope

@HomeScope
@Subcomponent(modules = arrayOf(HomeModule::class))
interface HomeComponent {

    fun inject(activity: HomeActivity)

    @Subcomponent.Builder
    interface Builder : SubcomponentBuilder<HomeComponent> {
        fun homeModule(module: HomeModule)
    }
}

@Module(includes = arrayOf(HomeModule.ModuleBinds::class))
class HomeModule(
        val fragmentManager: FragmentManager,
        val resProvider: ResourcesProvider
) {

    @Provides @HomeScope fun fragmentManager(): FragmentManager = fragmentManager
    @Provides @HomeScope fun resourcesProvider(): ResourcesProvider = resProvider

    @Module
    interface ModuleBinds {
        @Binds fun presenter(impl: HomePagesPresenter): HomePresenter
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope
