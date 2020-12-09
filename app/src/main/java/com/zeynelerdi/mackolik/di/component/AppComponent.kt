package com.zeynelerdi.mackolik.di.component

import com.zeynelerdi.mackolik.app.App
import com.zeynelerdi.mackolik.di.builder.ActivityBuilder
import com.zeynelerdi.mackolik.di.builder.FragmentBuilder
import com.zeynelerdi.mackolik.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}