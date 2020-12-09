package com.zeynelerdi.mackolik.di.module

import com.zeynelerdi.mackolik.app.initializers.AppInitializer
import com.zeynelerdi.mackolik.app.initializers.LoggerInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AppModuleBinds {

    @IntoSet
    @Binds
    abstract fun provideLogger(bind: LoggerInitializer): AppInitializer

}