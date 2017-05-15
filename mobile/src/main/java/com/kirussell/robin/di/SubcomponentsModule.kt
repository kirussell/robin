package com.kirussell.robin.di


import com.kirussell.base.di.subcomponents.SubcomponentBuilder
import com.kirussell.robin.curl.di.CurlComponent
import com.kirussell.robin.home.di.HomeComponent
import dagger.Binds

import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(HomeComponent::class, CurlComponent::class))
abstract class SubcomponentsModule {

    @Binds @IntoMap @ClassKey(HomeComponent::class)
    abstract fun homeComponentBuilder(builder: HomeComponent.Builder): SubcomponentBuilder<*>

    @Binds @IntoMap @ClassKey(CurlComponent::class)
    abstract fun curlComponentBuilder(builder: CurlComponent.Builder): SubcomponentBuilder<*>
}
