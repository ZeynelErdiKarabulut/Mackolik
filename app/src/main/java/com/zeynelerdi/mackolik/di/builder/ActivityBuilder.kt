package com.zeynelerdi.mackolik.di.builder

import com.zeynelerdi.mackolik.ui.browser.BrowserActivity
import com.zeynelerdi.mackolik.ui.browser.BrowserActivityModule
import com.zeynelerdi.mackolik.ui.main.MainActivity
import com.zeynelerdi.mackolik.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(BrowserActivityModule::class)])
    abstract fun bindBrowserActivity(): BrowserActivity

}