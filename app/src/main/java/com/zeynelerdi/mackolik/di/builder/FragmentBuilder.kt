package com.zeynelerdi.mackolik.di.builder

import com.zeynelerdi.mackolik.ui.livescore.LiveScoreFragment
import com.zeynelerdi.mackolik.ui.livescore.LiveScoreFragmentModule
import com.zeynelerdi.mackolik.ui.news.NewsFragment
import com.zeynelerdi.mackolik.ui.news.NewsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [(NewsFragmentModule::class)])
    abstract fun bindNewsFragment(): NewsFragment

    @ContributesAndroidInjector(modules = [(LiveScoreFragmentModule::class)])
    abstract fun bindLiveScoreFragment(): LiveScoreFragment
}