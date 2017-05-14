package com.kirussell.base.di.subcomponents


interface SubcomponentBuilder<out Component> {
    fun build(): Component
}
